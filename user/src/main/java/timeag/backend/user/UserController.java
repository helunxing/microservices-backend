package timeag.backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;

import timeag.backend.user.data.UserJpaRepository;
import timeag.backend.user.data.User;

@RestController
public class UserController {

    private final UserJpaRepository repository;

    public UserController(UserJpaRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/")
    public String root() {
        return "user microservice running";
    }

    @GetMapping(path = "/users")
    public ResponseEntity<Object> getAllUser() {
        Iterable<User> allUser = repository.findAll();
        if (!allUser.iterator().hasNext()) {
            return Responses.NotFoundEntity();
        }
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<User> newUser(@RequestBody User user) {

        Logger.getLogger("newUser").info(user.toString());

        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable long id) {

        Optional<User> findResult = repository.findById(id);

        if (findResult.isEmpty()) {
            return Responses.NotFoundEntity();
        }

        return new ResponseEntity<>(findResult.get(), HttpStatus.OK);
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable long id, @RequestBody User user) {

        Optional<User> findResult = repository.findById(id);

        if (findResult.isEmpty()) {
            return Responses.NotFoundEntity();
        }

        user.setId(id);

        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
