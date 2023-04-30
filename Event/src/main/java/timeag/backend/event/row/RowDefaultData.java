package timeag.backend.event.row;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RowDefaultData implements CommandLineRunner {

    @Autowired
    private RowsJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Event("tt", 1, "dt", "timeOptions"));
        for (Event row : repository.findAll()) {
            Logger.getLogger("RowDefaultData").info(row.toString());
        }
    }
}
