package timeag.backend.event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;

import timeag.backend.event.data.EventJpaRepository;
import timeag.backend.event.data.Event;

@RestController
public class EventController {

    private final EventJpaRepository repository;

    public EventController(EventJpaRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/")
    public String root() {
        return "event microservice running";
    }

    @GetMapping(path = "/events")
    public ResponseEntity<Object> getAllEvents() {
        Iterable<Event> allEvent = repository.findAll();
        if (!allEvent.iterator().hasNext()) {
//            maybe return 404 is not a good idea?
            return Responses.NotFoundEntity("There is no event in database.");
        }
        return new ResponseEntity<>(allEvent, HttpStatus.OK);
    }

    @PostMapping(path = "/event")
    public ResponseEntity<Event> newEvent(@RequestBody Event event) {

        Logger.getLogger("newEvent").info(event.toString());

        Event savedEvent = repository.save(event);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEvent.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping(path = "/event/{id}")
    public ResponseEntity<Object> getEvent(@PathVariable long id) {

        Optional<Event> findResult = repository.findById(id);

        if (findResult.isEmpty()) {
            return Responses.NotFoundEntity("Event with id " + id + " not found.");
        }

        return new ResponseEntity<>(findResult.get(), HttpStatus.OK);
    }

    @PutMapping(path = "/event/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable long id, @RequestBody Event event) {

        Optional<Event> findResult = repository.findById(id);

        if (findResult.isEmpty()) {
            return Responses.NotFoundEntity("Event with id " + id + " not found.");
        }

        event.setId(id);
        Event oldEvent = findResult.get();
        oldEvent.updateEvent(event);
        repository.save(oldEvent);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping(path = "/event/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/vote/{id}")
    public ResponseEntity<Object> upVote(@PathVariable long id, @RequestBody Event requestEvent) {
        Optional<Event> dBFoundEvent = repository.findById(id);

        if (dBFoundEvent.isEmpty()) {
            return Responses.NotFoundEntity("Event with id " + id + " not found.");
        }

        Event eventInDB = dBFoundEvent.get();
        eventInDB.voteTimeOption(requestEvent.getTimeOptions());
        repository.save(eventInDB);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
