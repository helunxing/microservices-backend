package timeag.backend.joinInfo.data;

import jakarta.persistence.*;

@Entity(name = "Join_table")
public class Join {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "event_id")
    private long eventId;

    @Column(name = "options_str")
    private String optionsStr;

    public Join(long id, long userId, long eventId, String optionsStr) {
        super();
        this.setId(id);
        this.setUserId(userId);
        this.setEventId(eventId);
        this.setOptionsStr(optionsStr);
    }

    public Join(long userId, long eventId, String optionsStr) {
        super();
        this.setUserId(userId);
        this.setEventId(eventId);
        this.setOptionsStr(optionsStr);
    }

    @Override
    public String toString() {
        return String.format("Join [id=%s, userId=%s, eventId=%s, optionsStr=%s]",
                id, userId, eventId, optionsStr);
    }

    public String getOptionsStr() {
        return optionsStr;
    }

    public void setOptionsStr(String optionsStr) {
        this.optionsStr = optionsStr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public Join() {
    }

}
