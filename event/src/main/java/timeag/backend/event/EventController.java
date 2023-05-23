package timeag.backend.event;

import org.springframework.beans.factory.annotation.Autowired;

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

    private ResponseEntity<Object> WrongFormatEntity() {
        // BFF layer only read the status code, "not found" is just a reminder for user.
        return new ResponseEntity<>("Your request format wrong", HttpStatus.PRECONDITION_FAILED);
    }

    private ResponseEntity<Object> NotFoundEntity() {
        // BFF layer only read the status code, "not found" is just a reminder for user.
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @Autowired
    private EventJpaRepository repository;

    @GetMapping(path = "/")
    public String root() {
        return "running";
    }

    //    get data by id
    @GetMapping(path = "/event/{id}")
    public ResponseEntity<Object> getEvent(@PathVariable String id) {
        long longId = 0;

        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return WrongFormatEntity();
        }

        Optional<Event> findResult = repository.findById(longId);

        if (findResult.isEmpty()) {
            return NotFoundEntity();
        }

        return new ResponseEntity<>(findResult.get(), HttpStatus.OK);
    }

    //    get data in json format
    @GetMapping(path = "/events")
    public ResponseEntity<Object> getAllEvents() {
        Iterable<Event> allEvent = repository.findAll();
        if (allEvent.iterator().hasNext()) {
            return new ResponseEntity<>(allEvent, HttpStatus.OK);
        }
        return NotFoundEntity();
    }

    //    post request data in json body to insert a new row
    @PostMapping(path = "/event")
    public ResponseEntity<Event> newEvent(@RequestBody Event event) {

        Logger.getLogger("newEvent").info(event.toString());

        Event savedEvent = repository.save(event);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/event/{id}")
                .buildAndExpand(savedEvent.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping(path = "/event/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable String id, @RequestBody Event event) {
        long longId = 0;

        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return WrongFormatEntity();
        }

        Optional<Event> findResult = repository.findById(longId);

        if (findResult.isEmpty()) {
            return NotFoundEntity();
        }

        event.setId(longId);

        Event savedEvent = repository.save(event);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/event/{id}")
                .buildAndExpand(savedEvent.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping(path = "/event/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable String id) {
        long longId = 0;

        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return WrongFormatEntity();
        }

        repository.deleteById(longId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
