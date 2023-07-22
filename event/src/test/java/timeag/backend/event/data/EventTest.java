package timeag.backend.event.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private Event event;

    @BeforeEach
    public void setUp() {
        event = new Event("title", 1, "date", "time1,time2,time3", "address");
    }

    @DisplayName("test create votes counts")
    @Test
    void createVotesCounts() {
        assertEquals("0,0,0", event.getVotesCounts());
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

    @DisplayName("test merge new time options")
    @Test
    void mergeNewTimeOptions() {
        event.voteTimeOption("time2");
        event.voteTimeOption("time3");
        event.voteTimeOption("time4");
        event.mergeNewTimeOptions("time1,time3,time4");
        assertEquals("time1,time3,time4", event.getTimeOptions());
        event.voteTimeOption("time4");
        assertEquals("0,1,1", event.getVotesCounts());
    }
}
