package ladder.model;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import org.synyx.hades.domain.support.AbstractAuditable;

/**
 * Abstract auditable superclass defining some standard fields for entities.
 * @author Marcus Krassmann
 */
@MappedSuperclass
public class AbstractEntity extends AbstractAuditable<User, Long> implements Serializable {

    @Override
    public String toString() {
        return "Id: " + getId();
    }
}
