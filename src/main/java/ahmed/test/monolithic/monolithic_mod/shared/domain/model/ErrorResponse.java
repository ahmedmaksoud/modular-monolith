package ahmed.test.monolithic.monolithic_mod.shared.domain.model;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorResponse{

	private String mainErrorCode;
	private String message;
	private List<MessageDetails> errors;
	private HttpStatus status;
	private String extraData;


    public String getMainErrorCode() {
        return mainErrorCode;
    }

    public void setMainErrorCode(String mainErrorCode) {
        this.mainErrorCode = mainErrorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MessageDetails> getErrors() {
        return errors;
    }

    public void setErrors(List<MessageDetails> errors) {
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }
}