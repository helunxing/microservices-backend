package hlx.microservices.helloworldrestapi;

import hlx.microservices.helloworldrestapi.row.Rows;
import hlx.microservices.helloworldrestapi.row.RowsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class HelloWorldController {

    @Autowired
    private RowsJpaRepository repository;

    private final String greeting = "Hello form microservices v1";

    @GetMapping(path = "/")
    public String root() {
        return "running";
    }

    @GetMapping(path = "/hel")
    public String helloWorld() {
        return greeting;
    }

    @GetMapping(path = "/hello-api/{path}")
    public HelloWorldBean helloWorldBean(
            @PathVariable String path
    ) {
        return new HelloWorldBean(greeting + ' ' + path);
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        repository.save(new Rows(1, name, String.format("%s, %s", greeting, name)));
        return new HelloWorldBean(String.format("%s, %s", greeting, name));
    }

    //get data in json format
    @GetMapping(path = "/rows")
    public Iterable<Rows> getRows() {
        return repository.findAll();
    }

    //    get data by id
    @GetMapping(path = "/rows/{id}")
    public Rows getRow(@PathVariable long id) {
        return repository.findById(id).get();
    }

    //post request data in json body to insert a new row
    @PostMapping(path = "/rows")
    public ResponseEntity<Rows> newRow(@RequestBody Rows row) {

        System.out.println(row);

        Rows savedRow = repository.save(row);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRow.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }


}
