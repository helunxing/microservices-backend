package hlx.microservices.newendpoint;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// setting CURRENCY_EXCHANGE_URI
@FeignClient(name = "hello-world-rest-api", url = "${HELLO_URI:http://localhost}:8000")
public interface HelloWorldProxy {

    @GetMapping("/hello-api")
    public HelloMessage retrieveExchangeValue(
//            @PathVariable String from,
//            @PathVariable String to
    );

}
