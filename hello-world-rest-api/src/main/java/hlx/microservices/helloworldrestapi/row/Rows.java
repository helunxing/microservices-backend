package hlx.microservices.helloworldrestapi.row;

import jakarta.persistence.*;

@Entity(name = "schema_table")
public class Rows {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "time_options")
    private String timeOptions;

    @Column(name = "title")
    private String title;

    public Rows() {
    }

    public Rows(long id, String key, String title) {
        super();
        this.id = id;
        this.timeOptions = key;
        this.title = title;
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

    @Override
    public String toString() {
        return "Rows{" +
                "id=" + id +
                ", key='" + timeOptions + '\'' +
                ", value='" + title + '\'' +
                '}';
    }
}
