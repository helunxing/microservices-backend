package timeag.backend.event.data;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

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

    @Column(name = "address")
    private String address;

    @Column(name = "votes_counts")
    private String votesCounts;

    public Event() {
    }

    public Event(long id, String title, long creatorId, String date, String timeOptions, String address) {
        super();
        this.setId(id);
        this.setTitle(title);
        this.setCreatorId(creatorId);
        this.setDate(date);
        this.setTimeOptions(timeOptions);
        this.setAddress(address);
        // create default votesCounts
        int numberOfOptions = this.getTimeOptions().split(",").length;
        String emptyVoteStr = String.join(",", Collections.nCopies(numberOfOptions, "0"));
        this.setVotesCounts(emptyVoteStr);
    }

    public Event(String title, long creatorId, String date, String timeOptions, String address) {
        super();
        this.setTitle(title);
        this.setCreatorId(creatorId);
        this.setDate(date);
        this.setTimeOptions(timeOptions);
        this.setAddress(address);
        // create default votesCounts
        int numberOfOptions = this.getTimeOptions().split(",").length;
        String emptyVoteStr = String.join(",", Collections.nCopies(numberOfOptions, "0"));
        this.setVotesCounts(emptyVoteStr);
    }

    public Event(long id, String timeOptions) {
        super();
        this.setId(id);
        this.setTimeOptions(timeOptions);
    }

    public void voteTimeOption(String input_timeOptions) {
        List<String> inputVoteArr = List.of(input_timeOptions.split(","));
        List<String> timeOptionsArr = List.of(this.getTimeOptions().split(","));
        List<Integer> votesCountsArr = new ArrayList<>(Stream.of(this.getVotesCounts().split(","))
                .map(Integer::parseInt).toList());

        for (String inputVote : inputVoteArr) {
            int index = timeOptionsArr.indexOf(inputVote);
            if (index == -1) {
                Logger.getLogger("EventVoteChange").warning(
                        String.format("target single vote '%s' not in Options. ignore it",
                                inputVote));
                continue;
            }
            votesCountsArr.set(index, votesCountsArr.get(index) + 1);
        }

        this.setVotesCounts(String.join(",", votesCountsArr.stream().map(String::valueOf).toList()));

        Logger.getLogger("EventVoteChange").info(
                String.format("voteTimeOption: %s. votesCounts result: %s",
                        input_timeOptions, this.getVotesCounts()));

    }

    public void mergeNewTimeOptions(String input_timeOptions) {
        List<String> inputVoteArr = List.of(input_timeOptions.split(","));
        List<Integer> oldVotesCountsArr = new ArrayList<>(Stream.of(this.getVotesCounts().split(","))
                .map(Integer::parseInt).toList());
        List<String> timeOptionsArr = List.of(this.getTimeOptions().split(","));
        List<Integer> newVotesCountsArr = new ArrayList<>();

        for (String inputVote : inputVoteArr) {
            int index = timeOptionsArr.indexOf(inputVote);
            if (index == -1) {
                newVotesCountsArr.add(0);
                Logger.getLogger("mergeNewTimeOptions").info(
                        String.format("target single option '%s' is not present in old. create 0 for it",
                                inputVote));
                continue;
            }

            Integer oldVoteCount = oldVotesCountsArr.get(index);
            newVotesCountsArr.add(oldVoteCount);
            Logger.getLogger("mergeNewTimeOptions").info(
                    String.format("target single option '%s' vote count have move to new. keep '%s' value",
                            inputVote, oldVoteCount));
        }

        this.setTimeOptions(input_timeOptions);
        this.setVotesCounts(String.join(",", newVotesCountsArr.stream().map(String::valueOf).toList()));

        Logger.getLogger("mergeNewTimeOptions").info(
                String.format("done. target options: %s. result event: %s",
                        input_timeOptions, this));
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVotesCounts() {
        return votesCounts;
    }

    private void setVotesCounts(String votesCounts) {
        this.votesCounts = votesCounts;
    }

    @Override
    public String toString() {
        return String.format(
                "Rows [id=%s, title=%s, creator_id=%s, address=%s, date=%s, timeOptions=%s, votesCounts=%s]",
                id, title, creatorId, address, date, timeOptions, votesCounts);
    }


}
