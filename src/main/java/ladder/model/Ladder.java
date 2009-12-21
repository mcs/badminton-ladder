package ladder.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "tLadder")
public class Ladder extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ladder_id")
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
    /**
     * Returns the amount of players in this ladder.
     * 
     * @return the size of the ladder
     */
    public int size() {
        return players.size();
    }

    /**
     * Gets the player on the given rank. Minimum value must be 1.
     * 
     * @param rank the rank
     * @return the player
     * @throws IllegalArgumentException if rank &lt; 1 or rank &gt; ladder's size
     */
    public Player getPlayer(int rank) {
        if (!isRankValidForLadder(rank)) {
            throw new IllegalArgumentException(rank + " not a valid rank for this ladder");
        }
        return players.get(rank - 1);
    }

    /**
     * Returns the rank of the passed player.
     * 
     * @param player the player
     * @return the player's rank in the ladder
     * @throws IllegalArgumentException if the player is not part of the ladder
     */
    public int getRank(Player player) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException(player + " not in ladder");
        }
        return players.indexOf(player) + 1;
    }

    /**
     * Re-ranks a player in the ladder.
     * 
     * @param player the player
     * @param rank the new rank
     * @throws IllegalArgumentException if the player is not in the ladder
     * @throws IllegalArgumentException if the rank is not valid
     */
    public void setRank(Player player, int rank) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException(player + " not part of ladder");
        }
        if (!isRankValidForLadder(rank)) {
            throw new IllegalArgumentException(rank + " not a valid rank for this ladder");
        }
        players.remove(player);
        players.add(rank - 1, player);
    }

    /**
     * Adds a new player to the ladder.
     * 
     * @param player the new player
     * @throws IllegalArgumentException if the player is already in the ladder
     */
    public void add(Player player) {
        if (players.contains(player)) {
            throw new IllegalArgumentException(player + " already in ladder");
        }
        players.add(player);
        player.setLadder(this);
    }

    /**
     * Removes a player from the ladder.
     * 
     * @param player the player to remove
     * @throws IllegalArgumentException if the player was not in the ladder
     */
    public void remove(Player player) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException(player + " not in ladder");
        }
        players.remove(player);
        player.setLadder(null);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ladder other = (Ladder) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    private boolean isRankValidForLadder(int rank) {
        return rank >= 1 && rank <= players.size();
    }
}
