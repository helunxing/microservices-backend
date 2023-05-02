package timeag.backend.user.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UserDefaultData implements CommandLineRunner {

    @Autowired
    private UserJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        repository.save(new Event("tt", 1, "dt", "timeOptions"));
        Logger.getLogger("UserDefaultData").info("----------------ALL DATA---------------");
        for (User user : repository.findAll()) {
            Logger.getLogger("UserDefaultData").info(user.toString());
        }
    }
}
