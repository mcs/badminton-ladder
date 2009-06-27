package ladder.model;

import java.io.Serializable;
import javax.persistence.Entity;
import org.synyx.hades.domain.support.AbstractAuditable;

@Entity
public class Actionlog extends AbstractAuditable<User,Long> implements Serializable {

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
