package hlx.microservices.newendpoint;

public class HelloMessage {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HelloMessage(String message) {
        super();
        this.message = message;
    }

    public HelloMessage() {
    }
}
