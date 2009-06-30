package ladder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Player extends AbstractEntity {

    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "ladder_id", insertable = false, updatable = false, nullable = false)
    private Ladder ladder;

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.ladder != other.ladder && (this.ladder == null || !this.ladder.equals(other.ladder))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 59 * hash + (this.ladder != null ? this.ladder.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("'%s' in ladder '%s'", name, ladder);
    }
}
