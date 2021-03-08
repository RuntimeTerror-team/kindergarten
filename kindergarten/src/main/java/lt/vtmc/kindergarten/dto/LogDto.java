package lt.vtmc.kindergarten.dto;

public class LogDto {

    private String loggingMessage;

    public LogDto(String loggingMessage) {
        this.loggingMessage = loggingMessage;
    }

    public LogDto() {

    }

    public String getLoggingMessage() {
        return loggingMessage;
    }

    public void setLoggingMessage(String loggingMessage) {
        this.loggingMessage = loggingMessage;
    }
}
