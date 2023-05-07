package timeag.backend.event;

public class EventBean {

    private String message;

    public EventBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("EventBean [message=%s]", message);
    }

}
