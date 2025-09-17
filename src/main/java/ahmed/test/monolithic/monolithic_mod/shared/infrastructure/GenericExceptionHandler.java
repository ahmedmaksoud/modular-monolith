package ahmed.test.monolithic.monolithic_mod.shared.infrastructure;

import ahmed.test.monolithic.monolithic_mod.shared.IApplogger;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AppMessage;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.ErrorResponse;
import ahmed.test.monolithic.monolithic_mod.shared.domain.model.MessageDetails;
import ahmed.test.monolithic.monolithic_mod.shared.exception.AppException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GenericExceptionHandler {


    private final MessageSource messageSource;
    private IApplogger log  = AppLoggers.get(this.getClass());

    public GenericExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({ AppException.class})
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(Exception e, WebRequest request) {
        AppException ex = null;
        //UserDTO userDTO = jwtUtils.getUserInfoFromJwtToken(context);
        log.debug("ServiceException  1 " + e.getMessage());
        if (e instanceof AppException) {
            ex = (AppException) e;
        }
       // ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse();
        Optional<List<AppMessage>> errorDTOs = ex.getErrorCodes();
        List<MessageDetails> errors = new ArrayList<MessageDetails>();
        Locale loc = getReqLocale(request);
        if (errorDTOs != null)
            errors = errorDTOs.get().stream()
                    .map(element -> new MessageDetails(
                            getMessageResource(element.getMessageCode(), loc,
                                    element.getParams()
                            ),
                            element.getMessageType(), element.getFieldName(), element.getLinkUrl(),
                            element.getMessageCode()))
                    .collect(Collectors.toList());
        if (errors.isEmpty())
            errors.add(new MessageDetails(getMessageResource("general.exception", loc, null) + "  " +ex.getMessage(),
                    AppMessage.MessageTypes.ERROR, "", "", ""));
        errorResponse.setMainErrorCode("APP_BR_10000");
        errorResponse.setMessage(ex.getExceptionMessage());
        errorResponse.setErrors(errors);
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setExtraData("{}");
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);

    }
    /**
     * This method replace params in massage with values from DTO
     * @param params
     * @param massage
     * @return massage
     */
    private String setParamterMessageResource(String[] params, String massage) {
        if (params!= null)
            for (int i = 0; i < params.length; i++)
                massage = massage.replace("{" + i + "}", params[i]);

        return massage;
    }

    private Locale getReqLocale(WebRequest request) {
        return request.getLocale() != null ? request.getLocale() : new Locale("en", "US");

    }

    private String getMessageResource(String errorCode, Locale locale, String[] params) {

        String message = messageSource.getMessage(errorCode, params, locale);

        //message =setParamterMessageResource(params, message);
        return message;
    }
}
