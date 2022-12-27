package hlx.microservices.newendpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EndPointController {
    private final String greeting = "Hello form microservices v1";

    @Autowired
    private HelloWorldProxy proxy;

    @GetMapping(path = "/")
    public String root() {
        return "running";
    }

    @GetMapping(path = "/point")
    public HelloMessage point() {
        return proxy.retrieveExchangeValue();
    }

}
