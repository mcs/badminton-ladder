
package ladder.model;

import ladder.BaseTestFixture;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LadderTest extends BaseTestFixture {

    private Ladder ladder;
    
    public LadderTest() {
    }

    @Before
    public void init() {
        ladder = ladderDao.readByPrimaryKey(1L);
    }

    @Test
    public void shouldKnowItsSize() {
        // when
        int size = ladder.size();

        // then
        assertThat(size, is(16));
    }

    @Test
    public void shouldKnowPlayersRank() {
        // given
        Player player = ladder.getPlayers().get(0);
        
        // when
        int rank = ladder.getRank(player);
        
        // then
        assertThat(rank, is(1));
    }

    @Test
    public void shouldRerankPlayer() {
        // given
        Player p = ladder.getPlayers().get(0);
        assertThat(ladder.getRank(p), is(1));
        int newRank = 3;

        // when
        ladder.setRank(p, newRank);

        // then
        assertThat(ladder.getRank(p), is(3));
        assertThat(ladder.size(), is(16));
    }

    @Test
    public void shouldAddNewPlayer() {
        // given
        Player player = new Player();

        // when
        ladder.add(player);

        // then
        assertThat(ladder.size(), is(17));
        assertThat(ladder.getPlayers().contains(player), is(true));
    }

    @Test
    public void shouldRemove() {
        // given
        Player player = ladder.getPlayers().get(0);

        // when
        ladder.remove(player);

        // then
        assertThat(ladder.getPlayers().contains(player), is(false));
        assertThat(ladder.size(), is(15));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowGetPlayerTooSmallRank() {
        ladder.getPlayer(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowGetPlayerWithTooBigRank() {
        ladder.getPlayer(ladder.size() + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowSetRankTooSmallRank() {
        ladder.setRank(ladder.getPlayer(1), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowSetRankWithTooBigRank() {
        ladder.setRank(ladder.getPlayer(1), ladder.size() + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowSetRankWithInvalidPlayer() {
        ladder.setRank(new Player(), 1);
    }
}