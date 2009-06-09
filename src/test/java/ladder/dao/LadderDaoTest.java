
package ladder.dao;

import ladder.BadmintonTestFixture;
import ladder.model.Ladder;
import ladder.model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LadderDaoTest extends BadmintonTestFixture {

    public LadderDaoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPersistedLadder() {
        Ladder ladder = ladderDao.findAll().get(0);
        System.out.println(ladder);
        assertThat(ladder.size(), is(10));
        for (Player p : ladder.getPlayers()) {
            assertThat(p.getId(), is(notNullValue()));
        }
    }

    @Test
    public void testOneToManyRelation() {
        Ladder ladder = new Ladder();
        assertThat(ladder.getId(), is(nullValue()));
        ladderDao.persist(ladder);
        assertThat(ladder.getId(), is(notNullValue()));
        assertThat(ladder.getCreated(), is(notNullValue()));
        assertThat(ladder.getUpdated(), is(nullValue()));
    }
}