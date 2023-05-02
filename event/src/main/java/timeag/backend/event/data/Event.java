package timeag.backend.event.data;

import jakarta.persistence.*;

@Entity(name = "event_table")
public class Event {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;


//    @ManyToOne
    @Column(name = "creator_id")
    private long creatorId;

    @Column(name = "date")
    private String date;

    @Column(name = "time_options")
    private String timeOptions;

    @Column(name = "title")
    private String title;

    public Event() {
    }

    public Event(long id, String title, long creatorId, String date, String timeOptions) {
        super();
        this.setId(id);
        this.setTitle(title);
        this.setCreatorId(creatorId);
        this.setDate(date);
        this.setTimeOptions(timeOptions);
    }

    public Event(String title, long creatorId, String date, String timeOptions) {
        super();
        this.setTitle(title);
        this.setCreatorId(creatorId);
        this.setDate(date);
        this.setTimeOptions(timeOptions);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimeOptions() {
        return timeOptions;
    }

    public void setTimeOptions(String key) {
        this.timeOptions = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creator_id) {
        this.creatorId = creator_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format(
                "Rows [id=%s, title=%s, creator_id=%s, date=%s, timeOptions=%s]",
                id, title, creatorId, date, timeOptions);
    }
}
