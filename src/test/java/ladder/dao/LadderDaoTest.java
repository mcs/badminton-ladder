package ladder.dao;

import ladder.BaseTestFixture;
import ladder.model.Ladder;
import ladder.model.Player;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LadderDaoTest extends BaseTestFixture {

    @Test
    public void shouldFindNamedLadder() {
        // given
        long id = 1;

        // when
        Ladder ladder = ladderDao.readByPrimaryKey(id);

        // then
        assertThat(ladder.size(), is(16));
        for (Player p : ladder.getPlayers()) {
            assertThat(p.getId(), is(notNullValue()));
        }
    }

    @Test
    public void testOneToManyRelation() {
        Ladder ladder = new Ladder();
        ladder.setName("TempName");
        assertThat(ladder.getId(), is(nullValue()));
        ladderDao.save(ladder);
        assertThat(ladder.getId(), is(notNullValue()));
    }
}