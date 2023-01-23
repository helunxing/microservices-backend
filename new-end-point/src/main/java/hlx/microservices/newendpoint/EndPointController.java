package hlx.microservices.newendpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EndPointController {

    private Logger logger = LoggerFactory.getLogger(EndPointController.class);

    private final String greeting = "Hello form microservices v1";

    @Autowired
    private HelloWorldProxy proxy;

    @GetMapping(path = "/")
    public String root() {
        return "running";
    }

    @GetMapping(path = "/point/{path}")
    public HelloMessage point(
            @PathVariable String path,
            @RequestParam(value = "name", defaultValue = "World") String name) {

        logger.info("path is {}, name is {}", path, name);

        return proxy.retrieveExchangeValue(path);
    }

}
