package timeag.backend.joinInfo.data;

import jakarta.persistence.*;

@Entity(name = "Join_table")
public class Join {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "user_id")
    private long joinerID;

    @Column(name = "event_id")
    private long eventID;

    @Column(name = "options_str")
    private String selectedStr;

    public Join(long id, long joinerID, long eventID, String selectedStr) {
        super();
        this.setId(id);
        this.setJoinerID(joinerID);
        this.setEventID(eventID);
        this.setSelectedStr(selectedStr);
    }

    public Join(long joinerID, long eventID, String selectedStr) {
        super();
        this.setJoinerID(joinerID);
        this.setEventID(eventID);
        this.setSelectedStr(selectedStr);
    }

    @Override
    public String toString() {
        return String.format("Join [id=%s, userId=%s, eventId=%s, optionsStr=%s]",
                id, joinerID, eventID, selectedStr);
    }

    public String getSelectedStr() {
        return selectedStr;
    }

    public void setSelectedStr(String optionsStr) {
        this.selectedStr = optionsStr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJoinerID() {
        return joinerID;
    }

    public void setJoinerID(long userId) {
        this.joinerID = userId;
    }

    public long getEventID() {
        return eventID;
    }

    public void setEventID(long eventId) {
        this.eventID = eventId;
    }

    public Join() {
    }

}
