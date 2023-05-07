package timeag.backend.event.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EventDefaultData implements CommandLineRunner {

    @Autowired
    private EventJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        repository.save(new Event("tt", 1, "dt", "timeOptions"));
        Logger.getLogger("EventDefaultData").info("----------------ALL DATA---------------");
        for (Event event : repository.findAll()) {
            Logger.getLogger("EventDefaultData").info(event.toString());
        }
    }
}
