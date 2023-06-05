package timeag.backend.user;

import org.springframework.beans.factory.annotation.Autowired;

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

    private ResponseEntity<Object> WrongFormatEntity() {
        // BFF layer only read the status code, "not found" is just a reminder for user.
        return new ResponseEntity<>("Your request format wrong", HttpStatus.PRECONDITION_FAILED);
    }

    private ResponseEntity<Object> NotFoundEntity() {
        // BFF layer only read the status code, "not found" is just a reminder for user.
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @Autowired
    private UserJpaRepository repository;

    @GetMapping(path = "/")
    public String root() {
        return "user microservice running";
    }

    @GetMapping(path = "/users")
    public ResponseEntity<Object> getAllUser() {
        Iterable<User> allUser = repository.findAll();
        if (!allUser.iterator().hasNext()) {
            return NotFoundEntity();
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
    public ResponseEntity<Object> getUser(@PathVariable String id) {
        long longId = 0;

        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return WrongFormatEntity();
        }

        Optional<User> findResult = repository.findById(longId);

        if (findResult.isEmpty()) {
            return NotFoundEntity();
        }

        return new ResponseEntity<>(findResult.get(), HttpStatus.OK);
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody User user) {
        long longId = 0;

        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return WrongFormatEntity();
        }

        Optional<User> findResult = repository.findById(longId);

        if (findResult.isEmpty()) {
            return NotFoundEntity();
        }

        user.setId(longId);

        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
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
