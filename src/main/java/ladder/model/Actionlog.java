package ladder.model;

import javax.persistence.Entity;

@Entity
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
