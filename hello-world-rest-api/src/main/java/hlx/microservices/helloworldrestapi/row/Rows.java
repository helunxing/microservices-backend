package hlx.microservices.helloworldrestapi.row;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "schema_table")
public class Rows {
    @Id
    private long id;

    @Column(name = "k")
    private String key;

    @Column(name = "v")
    private String value;

    public Rows() {
    }

    public Rows(long id, String key, String value) {
        super();
        this.id = id;
        this.key = key;
        this.value = value;
    }
}
