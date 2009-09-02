package ladder.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.hibernate.annotations.IndexColumn;

@Entity
public class Ladder extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "ladder")
    @IndexColumn(name = "player_rank")
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
        player.setLadder(this);
    }

    public void remove(Player player) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException(player + " not in ladder!");
        }
        players.remove(player);
        player.setLadder(null);
    }

    @Override
    public String toString() {
        return "Ladder " + getId() + "(" + players.size() + " players)";
    }
}
