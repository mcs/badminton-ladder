package ladder.dao;

import java.util.List;
import ladder.BaseTestFixture;
import ladder.model.Player;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PlayerDaoTest extends BaseTestFixture {

    public PlayerDaoTest() {
    }

    @Test
    public void shouldDeliverAllPlayers() {
        // given
        List<Player> players = playerDao.readAll();

        // when
        int size = players.size();
        
        // then
        assertThat(size, is(20));
        for (Player p : players) {
            assertThat(p.getId(), is(notNullValue()));
        }
    }

    @Test
    public void shouldFindPlayerForExistingName() {
        // given
        String name = PLAYER_PREFIX + "1";
        
        // when
        Player p = playerDao.findByName(name);

        // then
        assertThat(p, is(notNullValue()));
    }

    @Test
    public void shouldFindNoPlayerForNonExistingName() {
        // given
        String name = PLAYER_PREFIX + "NO_NO_NO";

        // when
        Player p = playerDao.findByName(name);

        // then
        assertThat(p, is(nullValue()));
    }
}