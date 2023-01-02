package hlx.microservices.helloworldrestapi.row;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RowDefaultData implements CommandLineRunner {
    @Autowired
    private RowsJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Rows());
        System.out.println(repository.findAll());
    }
}
