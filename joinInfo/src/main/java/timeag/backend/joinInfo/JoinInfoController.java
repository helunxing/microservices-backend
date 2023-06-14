package timeag.backend.joinInfo;

import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import timeag.backend.joinInfo.data.Join;
import timeag.backend.joinInfo.data.JoinInfoJpaRepository;

@RestController
public class JoinInfoController {

    private final JoinInfoJpaRepository repository;

    public JoinInfoController(JoinInfoJpaRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/")
    public String root() {
        return "joinInfo microservice running";
    }

    @PostMapping(path = "/joinInfo")
    public ResponseEntity<Join> newJoinInfo(@RequestBody Join join) {
        Logger.getLogger("newJoinInfo").info(join.toString());
        Join savedJoinInfo = repository.save(join);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedJoinInfo.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/joinInfo/{id}")
    public ResponseEntity<Object> getJoinInfo(@PathVariable long id) {

        Optional<Join> findResult = repository.findById(id);

        if (findResult.isEmpty()) {
            return Responses.NotFoundEntity();
        }

        return new ResponseEntity<>(findResult.get(), HttpStatus.OK);
    }

    @PutMapping(path = "/joinInfo/{id}")
    public ResponseEntity<Object> updateJoinInfo(@PathVariable long id, @RequestBody Join join) {

        Optional<Join> findResult = repository.findById(id);

        if (findResult.isEmpty()) {
            return Responses.NotFoundEntity();
        }

        join.setId(id);

        Join savedJoinInfo = repository.save(join);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedJoinInfo.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/joinInfo/{id}")
    public ResponseEntity<Object> deleteJoinInfo(@PathVariable long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
