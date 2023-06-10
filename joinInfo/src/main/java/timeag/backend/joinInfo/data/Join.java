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

    public Join(long id, long userId, long eventId) {
        super();
        this.setId(id);
        this.setUserId(userId);
        this.setEventId(eventId);
    }

    public Join(long userId, long eventId) {
        super();
        this.setUserId(userId);
        this.setEventId(eventId);
    }

    @Override
    public String toString() {
        return "Join [id=" + id + ", userId=" + userId + ", eventId=" + eventId + "]";
    }
}
