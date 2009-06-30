package ladder.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Role extends AbstractEntity {

    @Column(unique = true)
    private String name;
    @ManyToMany
    private Set<Access> access;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Access> getAccess() {
        return access;
    }

    public void setAccess(Set<Access> access) {
        this.access = access;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Role)) {
            return false;
        }
        final Role other = (Role) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + ", Rolename: " + name;
    }



    public boolean hasRight(Access right) {
        return access.contains(right);
    }
}
