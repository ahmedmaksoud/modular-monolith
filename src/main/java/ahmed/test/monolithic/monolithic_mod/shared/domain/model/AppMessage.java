package ahmed.test.monolithic.monolithic_mod.shared.domain.model;

public class AppMessage {

    private final MessageTypes messageType;
    private final String messageKey;
    private final String[] params;
    private final String[] urlParams;
    private final String fieldName;
    private final String linkUrl;

    public AppMessage(MessageTypes messageType, String messageKey, String[] params,
                      String[] urlParams, String fieldName, String linkUrl) {
        this.messageType = messageType;
        this.messageKey = messageKey;
        this.params = params;
        this.urlParams = urlParams;
        this.fieldName = fieldName;
        this.linkUrl = linkUrl;
    }


    public enum MessageTypes {
        INFO(1), WARNING(2), ERROR(3), SHOW_STOPPER(4);

        public final int value;

        MessageTypes(int value) {
            this.value = value;
        }
    }

    public MessageTypes getMessageType() {
        return messageType;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String[] getParams() {
        return params;
    }

    public String[] getUrlParams() {
        return urlParams;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }
}
