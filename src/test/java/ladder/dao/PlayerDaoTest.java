
package ladder.dao;

import java.util.List;
import javax.persistence.NoResultException;
import ladder.BadmintonTestFixture;
import ladder.model.Player;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PlayerDaoTest extends BadmintonTestFixture {

    public PlayerDaoTest() {
    }

    @Test
    public void allPersistedFromFixture() {
        List<Player> players = playerDao.findAll();
        assertThat(players.size(), is(10));
        for (Player p : players) {
            assertThat(p.getId(), is(notNullValue()));
        }
    }

    @Test
    public void nameExists() {
        System.out.println("findByName");
        Player result = playerDao.findByName(PLAYER_PREFIX + "1");
        assertThat(result, is(notNullValue()));
    }

    @Test(expected = NoResultException.class)
    public void nameDoesNotExist() {
        System.out.println("findByName");
        playerDao.findByName(PLAYER_PREFIX + "NO_NO_NO");
    }
}