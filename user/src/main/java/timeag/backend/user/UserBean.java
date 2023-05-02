package timeag.backend.user;

public class UserBean {

    private String message;

    public UserBean(String message) {
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
