package ladder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.synyx.hades.domain.support.AbstractPersistable;

@Entity
public class Access extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String name;

    public Access() {
    }

    public Access(String name) {
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
        final Access other = (Access) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
}
