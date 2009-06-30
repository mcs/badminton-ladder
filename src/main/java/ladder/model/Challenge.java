package ladder.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Challenge extends AbstractEntity {

    @ManyToOne
    private Player challenger;
    @ManyToOne
    private Player challenged;

    public Challenge() {
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
}
