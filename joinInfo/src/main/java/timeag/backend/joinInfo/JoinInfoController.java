package timeag.backend.joinInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import timeag.backend.joinInfo.data.Join;
import timeag.backend.joinInfo.data.JoinInfoJpaRepository;

@RestController
public class JoinInfoController {

    @Autowired
    private JoinInfoJpaRepository repository;

    private ResponseEntity<Object> NotFoundEntity() {
        // BFF layer only read the status code, "not found" is just a reminder for user.
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }


    @GetMapping(path = "/")
    public String root() {
        return "joinInfo microservice running";
    }

    @GetMapping(path = "/joinInfos")
    public ResponseEntity<Object> getAllJoinInfos() {
        Iterable<Join> allEvent = repository.findAll();
        if (!allEvent.iterator().hasNext()) {
            return NotFoundEntity();
        }
        return new ResponseEntity<>(allEvent, HttpStatus.OK);
    }

}
