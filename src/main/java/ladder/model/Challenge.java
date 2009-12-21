package ladder.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tChallenge")
public class Challenge extends AbstractEntity {

    @ManyToOne
    private Player challenger;
    @ManyToOne
    private Player challenged;

    public Challenge() {
    }

    public Challenge(Player challenger, Player challenged) {
        this.challenger = challenger;
        this.challenged = challenged;
    }

    public Player getChallenger() {
        return challenger;
    }

    public void setChallenger(Player challenger) {
        this.challenger = challenger;
    }

    public Player getChallenged() {
        return challenged;
    }

    public void setChallenged(Player challenged) {
        this.challenged = challenged;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Challenge other = (Challenge) obj;
        if (this.challenger != other.challenger && (this.challenger == null || !this.challenger.equals(other.challenger))) {
            return false;
        }
        if (this.challenged != other.challenged && (this.challenged == null || !this.challenged.equals(other.challenged))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.challenger != null ? this.challenger.hashCode() : 0);
        hash = 29 * hash + (this.challenged != null ? this.challenged.hashCode() : 0);
        return hash;
    }
    
}
