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
        //insert a auto update id record

//        repository.save(new Rows("name1", "value1"));
//        repository.save(new Rows(1, "rename", "revalue"));
        for (Rows row : repository.findAll()) {
            System.out.println(row);
        }
    }
}
