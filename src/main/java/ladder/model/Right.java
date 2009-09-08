package ladder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.synyx.hades.domain.support.AbstractPersistable;

@Entity
@Table(name = "tRight")
public class Right extends AbstractPersistable<Long> {

    @Column(unique = true, nullable = false)
    private String name;
    private String description;

    public Right() {
    }

    public Right(String name) {
        this(name, null);
    }

    public Right(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
