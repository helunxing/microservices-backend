package timeag.backend.joinInfo;

import org.springframework.web.bind.annotation.*;

@RestController
public class JoinInfoController {

    @GetMapping(path = "/")
    public String root() {
        return "joinInfo microservice running";
    }

}
