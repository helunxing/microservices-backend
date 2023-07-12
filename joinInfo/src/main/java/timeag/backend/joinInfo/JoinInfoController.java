package timeag.backend.joinInfo;

import java.net.URI;
import java.util.List;
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

    //    @PostMapping(path = "/joinInfo")
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
        Optional<Join> joinInfo = repository.findById(id);
        if (joinInfo.isPresent()) {
            return new ResponseEntity<>(joinInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/joinInfo")
    public ResponseEntity<Object> updateJoinInfo(@RequestBody Join join) {

        List<Join> findResult = repository.findByEventUserPair(join.getEventId(), join.getUserId());
        Join savedJoinInfo;

        if (findResult.isEmpty()) {
            System.out.println(join.toString());
            savedJoinInfo = repository.save(join);
        } else {
            savedJoinInfo = findResult.remove(0);
//            Maybe I should return error if the list is longer than one.
            savedJoinInfo.setOptionsStr(join.getOptionsStr());
            savedJoinInfo = repository.save(savedJoinInfo);
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedJoinInfo.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/joinInfo/{id}")
    public ResponseEntity<Object> deleteJoinInfo(@PathVariable long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/joinInfo")
    public ResponseEntity<Object> deleteJoinInfoByID(@RequestBody Join join) {
        List<Join> findResult = repository.findByEventUserPair(join.getEventId(), join.getUserId());
        if (findResult.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            repository.deleteById(findResult.remove(0).getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
