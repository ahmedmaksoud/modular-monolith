package ahmed.test.monolithic.monolithic_mod.shared.exception;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.AppMessage;

import java.util.List;
import java.util.Optional;

public class AppException extends RuntimeException {

    public AppException(String s) {
        super(s);
    }


    /**
     *
     */
    private Optional<List<AppMessage>> messages ;
    private String exceptionMessage;
    private String extraData;

    public AppException(String exceptionMessage, Throwable e, Optional<List<AppMessage>> messages) {
        super(exceptionMessage, e);
        this.exceptionMessage = exceptionMessage;
        this.messages = messages;


    }

    public AppException(String exceptionMessage, Throwable e, Optional<List<AppMessage>> messages, String extraData) {
        this.exceptionMessage = exceptionMessage;
        this.messages = messages;
        this.extraData = extraData;

    }

    public Optional<List<AppMessage>> getErrorCodes() {
        return messages;
    }

    public String getExtraData() {
        return extraData;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
