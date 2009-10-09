
package ladder.service;

import java.util.List;
import ladder.BadmintonTestFixture;
import ladder.model.Ladder;
import ladder.model.Player;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PyramidLadderServiceTest extends BadmintonTestFixture {

    @Autowired
    private LadderService ladderService;

    @Test
    public void testEnterValidMatchResult() {
        System.out.println("enterMatchResult");
        Ladder ladder = ladderDao.readAll().get(0);
        Player winner = ladder.getPlayer(3);
        Player loser = ladder.getPlayer(2);
        assertThat(ladder.getRank(winner), is(3));
        assertThat(ladder.getRank(loser), is(2));
        ladderService.enterMatchResult(winner.getId(), loser.getId());
        assertThat(ladder.getRank(winner), is(2));
        assertThat(ladder.getRank(loser), is(3));
    }

    public void testEnterInvalidMatchResult() {
        System.out.println("enterMatchResult");
        Ladder ladder = ladderDao.readAll().get(0);
        Player winner = ladder.getPlayer(3);
        Player loser = ladder.getPlayer(1);
        ladderService.enterMatchResult(winner.getId(), loser.getId());
        assertThat(ladder.getRank(winner), is(1));
        assertThat(ladder.getRank(loser), is(2));
    }

    @Test
    public void testIsChallengeAllowed() {
        System.out.println("isChallengeAllowed");
        List<Player> players = playerDao.readAll();
        Player p1 = players.get(0);
        Player p2 = players.get(1);
        Player p3 = players.get(2);

        assertThat(ladderService.isChallengeAllowed(p2.getId(), p1.getId()), is(true));
        assertThat(ladderService.isChallengeAllowed(p1.getId(), p2.getId()), is(false));
        assertThat(ladderService.isChallengeAllowed(p3.getId(), p2.getId()), is(true));
        assertThat(ladderService.isChallengeAllowed(p2.getId(), p3.getId()), is(false));
        assertThat(ladderService.isChallengeAllowed(p1.getId(), p3.getId()), is(false));
        assertThat(ladderService.isChallengeAllowed(p3.getId(), p1.getId()), is(false));
        assertThat(ladderService.isChallengeAllowed(p1.getId(), p1.getId()), is(false));
    }

}