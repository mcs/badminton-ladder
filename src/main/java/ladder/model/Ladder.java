package ladder.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.IndexColumn;

@Entity
public class Ladder extends AbstractEntity {

    private String name = "";
    @OneToMany
    @IndexColumn(name = "player_rank")
    @JoinColumn(name = "ladder_id", nullable = false)
    private List<Player> players = new ArrayList<Player>();

    public Ladder() {
    }

    /* Entity methods */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /* Domain methods */
    
    public int size() {
        return players.size();
    }

    public Player getPlayer(int rank) {
        if (rank < 1 || rank > players.size()) {
            throw new IllegalArgumentException(rank + " not a valid rank for this ladder!");
        }
        return players.get(rank - 1);
    }

    public int getRank(Player player) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException(player + " not in ladder!");
        }
        return players.indexOf(player) + 1;
    }

    public void setRank(Player p, int newRank) {
        if (players.contains(p)) {
            this.remove(p);
        }
        players.add(newRank - 1, p);
    }

    public void add(Player player) {
        if (players.contains(player)) {
            throw new IllegalArgumentException(player + " already in ladder!");
        }
        players.add(player);
    }

    public void remove(Player player) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException(player + " not in ladder!");
        }
        players.remove(player);
    }

    @Override
    public String toString() {
        return "Ladder " + getId() + "(" + players.size() + " players)";
    }
}
