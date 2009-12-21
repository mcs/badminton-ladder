package ladder.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tPlayer", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "ladder_id"})
})
public class Player extends AbstractEntity {

    private String name;
    @ManyToOne
    /* 
     * nullable = true, because ladder_id is null in the moment when a player 
     * is re-ranked (remove => add)
     */
    @JoinColumn(name = "ladder_id", nullable = true, insertable = false, updatable = false)
    private Ladder ladder;
    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRank() {
        return ladder.getRank(this);
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
        return String.format("%s (%s)", name, ladder == null ? "<no ladder>" : ladder.getName());
    }
}
