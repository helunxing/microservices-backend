package timeag.backend.event;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.logging.Logger;

import timeag.backend.event.data.EventJpaRepository;
import timeag.backend.event.data.Event;

@RestController
public class EventController {

    @Autowired
    private EventJpaRepository repository;

    @GetMapping(path = "/")
    public String root() {
        return "running";
    }

    @GetMapping(path = "/hello/{path}")
    public EventBean helloWorldBean(@PathVariable String path) {
        return new EventBean(String.format("hello %s ", path));
    }

    //    get data by id
    @GetMapping(path = "/event/{id}")
    public Event getEvent(@PathVariable long id) {
        return repository.findById(id).get();
    }

    //    get data in json format
    @GetMapping(path = "/event")
    public Iterable<Event> getAllEvents() {
        return repository.findAll();
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

}
