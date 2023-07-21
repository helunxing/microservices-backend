package timeag.backend.event.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private static Event event;

    @BeforeEach
    public void setUp() {
        event = new Event();
        event.setTitle("title");
        event.setCreatorId(1);
        event.setDate("date");
        event.setTimeOptions("time1,time2,time3");
        event.setVotesCounts("0,0,0");
        event.setAddress("address");
    }

    @DisplayName("test vote for single time option")
    @Test
    void voteTimeOption() {
        event.voteTimeOption("time1");
        assertEquals("1,0,0", event.getVotesCounts());
        event.voteTimeOption("time2");
        assertEquals("1,1,0", event.getVotesCounts());
        event.voteTimeOption("time3");
        assertEquals("1,1,1", event.getVotesCounts());
        event.voteTimeOption("time3,time2");
        assertEquals("1,2,2", event.getVotesCounts());
    }
}
