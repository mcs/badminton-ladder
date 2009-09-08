
package ladder.model;

import ladder.BadmintonTestFixture;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LadderTest extends BadmintonTestFixture {

    private Ladder ladder;
    
    public LadderTest() {
    }

    @Before
    public void init() {
        ladder = ladderDao.readAll().get(0);
    }

    @Test
    public void testSize() {
        System.out.println("size");
        assertThat(ladder.size(), is(16));
    }

    @Test
    public void testGetRank() {
        System.out.println("getRank");
        Player player = ladder.getPlayers().get(0);
        assertThat(ladder.getRank(player), is(1));
    }

    @Test
    public void testSetRank() {
        System.out.println("setRank");
        Player p = ladder.getPlayers().get(0);
        assertThat(ladder.getRank(p), is(1));
        int newRank = 3;
        ladder.setRank(p, newRank);
        assertThat(ladder.getRank(p), is(3));
        assertThat(ladder.size(), is(16));
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        Player player = new Player();
        ladder.add(player);
        assertThat(ladder.size(), is(17));
        assertThat(ladder.getPlayers().contains(player), is(true));
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        Player player = ladder.getPlayers().get(0);
        ladder.remove(player);
        assertThat(ladder.getPlayers().contains(player), is(false));
        assertThat(ladder.size(), is(15));
    }
}