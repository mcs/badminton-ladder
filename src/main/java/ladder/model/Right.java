package ladder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.synyx.hades.domain.support.AbstractPersistable;

@Entity
public class Right extends AbstractPersistable<Long> {

    @Column(unique = true, nullable = false)
    private String name;

    public Right() {
    }

    public Right(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Right other = (Right) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
