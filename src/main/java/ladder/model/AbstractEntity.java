package ladder.model;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import org.synyx.hades.domain.support.AbstractPersistable;

@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<Long> implements Serializable  {

    @Override
    public String toString() {
        return "Id: " + getId();
    }

}
