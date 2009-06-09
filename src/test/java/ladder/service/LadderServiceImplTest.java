package ladder.service;

import java.util.List;
import ladder.BadmintonTestFixture;
import ladder.model.Ladder;
import ladder.model.Player;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LadderServiceImplTest extends BadmintonTestFixture {

    @Autowired
    private LadderService ladderService;

    public LadderServiceImplTest() {
    }

    @Test
    public void testRankPlayer() {
        System.out.println("rankPlayer");
        List<Ladder> ladders = ladderDao.findAll();
        assertThat(ladders.size(), is(1));
        Ladder ladder = ladders.get(0);
        Player p = playerDao.findByName(PLAYER_PREFIX + "1");
        int newRank = 1;
        ladderService.rankPlayer(ladder, p, newRank);
    }

}