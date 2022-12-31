package hlx.microservices.helloworldrestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    private final String greeting = "Hello form microservices v1";

    @GetMapping(path = "/")
    public String root() {
        return "running";
    }

    @GetMapping(path = "/hel")
    public String helloWorld() {
        return greeting;
    }

    @GetMapping(path = "/hello-api/{path}")
    public HelloWorldBean helloWorldBean(
            @PathVariable String path
    ) {
        return new HelloWorldBean(greeting + ' ' + path);
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("%s, %s", greeting, name));
    }

}
