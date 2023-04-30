package timeag.backend.event;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import timeag.backend.event.row.RowsJpaRepository;
import timeag.backend.event.row.Event;

@RestController
public class EventController {

    @Autowired
    private RowsJpaRepository repository;

    @GetMapping(path = "/")
    public String root() {
        return "running";
    }

    @GetMapping(path = "/feb/{path}")
    public EventBean helloWorldBean(@PathVariable String path) {
        return new EventBean(String.format("hello %s ", path));
    }

    //    get data by id
    @GetMapping(path = "/rows/{id}")
    public Event getRow(@PathVariable long id) {
        return repository.findById(id).get();
    }

    //    get data in json format
    @GetMapping(path = "/rows")
    public Iterable<Event> getRows() {
        return repository.findAll();
    }

    //    post request data in json body to insert a new row
    @PostMapping(path = "/rows")
    public ResponseEntity<Event> newRow(@RequestBody Event row) {

        System.out.println(row);

        Event savedRow = repository.save(row);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRow.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

}
