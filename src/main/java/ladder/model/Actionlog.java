package ladder.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tActionLog")
public class Actionlog extends AbstractEntity {

    private String description;

    public Actionlog() {
    }

    public Actionlog(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
