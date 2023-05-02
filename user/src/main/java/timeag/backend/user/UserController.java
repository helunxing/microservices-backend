package timeag.backend.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.logging.Logger;

import timeag.backend.user.data.UserJpaRepository;
import timeag.backend.user.data.User;

@RestController
public class UserController {

    @Autowired
    private UserJpaRepository repository;

    @GetMapping(path = "/")
    public String root() {
        return "runninga aaefe";
    }

    @GetMapping(path = "/hello/{path}")
    public UserBean helloWorldBean(@PathVariable String path) {
        return new UserBean(String.format("hello %s ", path));
    }

    //    get data by id
    @GetMapping(path = "/user/{id}")
    public User getUser(@PathVariable long id) {
        return repository.findById(id).get();
    }

    //    get data in json format
    @GetMapping(path = "/user")
    public Iterable<User> getAllUser() {
        return repository.findAll();
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
