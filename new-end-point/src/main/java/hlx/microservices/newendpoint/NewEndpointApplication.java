package hlx.microservices.newendpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NewEndpointApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewEndpointApplication.class, args);
    }
}
