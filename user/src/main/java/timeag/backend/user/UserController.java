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

    @GetMapping(path = "/hello/{path}")
    public UserBean helloWorldBean(@PathVariable String path) {
        return new UserBean(String.format("hello %s ", path));
    }

    //    get data by id
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable long id) {
        Optional<User> findResult = repository.findById(id);
        if (findResult.isEmpty()) {
            return NotFoundEntity();
        }
        return new ResponseEntity<>(findResult.get(), HttpStatus.OK);
    }

    //    get data in json format
    @GetMapping(path = "/users")
    public ResponseEntity<Object> getAllUser() {
        Iterable<User> allUser = repository.findAll();
        if (!allUser.iterator().hasNext()) {
            return NotFoundEntity();
        }
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    //    post request data in json body to insert a new row
    @PostMapping(path = "/user")
    public ResponseEntity<User> newUser(@RequestBody User user) {

        Logger.getLogger("newRow").info(user.toString());

        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/user/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

}
