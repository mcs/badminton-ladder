
package ladder.dao;

import ladder.BadmintonTestFixture;
import ladder.model.Ladder;
import ladder.model.Player;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LadderDaoTest extends BadmintonTestFixture {

    public LadderDaoTest() {
    }

    @Test
    public void testPersistedLadder() {
        Ladder ladder = ladderDao.readAll().get(0);
        System.out.println(ladder);
        assertThat(ladder.size(), is(16));
        for (Player p : ladder.getPlayers()) {
            assertThat(p.getId(), is(notNullValue()));
        }
    }

    @Test
    public void testOneToManyRelation() {
        Ladder ladder = new Ladder();
        assertThat(ladder.getId(), is(nullValue()));
        ladderDao.save(ladder);
        assertThat(ladder.getId(), is(notNullValue()));
    }
}