package lt.vtmc.kindergarten.dto;

public class LoggingDto {

    private String loggingMessage;

    public LoggingDto(String loggingMessage) {
        this.loggingMessage = loggingMessage;
    }

    public LoggingDto() {

    }

    public String getLoggingMessage() {
        return loggingMessage;
    }

    public void setLoggingMessage(String loggingMessage) {
        this.loggingMessage = loggingMessage;
    }
}
