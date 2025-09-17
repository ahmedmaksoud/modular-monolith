package ahmed.test.monolithic.monolithic_mod.shared.domain.model;

public class MessageDetails{

	private String message;
	private AppMessage.MessageTypes messageType;
	private String fieldName;
	private String linkUrl; 
	private String messageCode;

    public MessageDetails(String message, AppMessage.MessageTypes messageType, String fieldName, String linkUrl, String messageCode) {
        this.message = message;
        this.messageType = messageType;
        this.fieldName = fieldName;
        this.linkUrl = linkUrl;
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppMessage.MessageTypes getMessageType() {
        return messageType;
    }

    public void setMessageType(AppMessage.MessageTypes messageType) {
        this.messageType = messageType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }


}