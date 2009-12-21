package ladder.service;

import java.util.List;
import ladder.BaseTestFixture;
import ladder.model.Ladder;
import ladder.model.Player;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PyramidLadderServiceTest extends BaseTestFixture {

    @Autowired
    private PyramidLadderService ladderService;

    @Test
    public void shouldAllowValidChallenges() {
        // given
        List<Player> players = playerDao.readAll();
        Player p1 = players.get(0);
        Player p2 = players.get(1);
        Player p3 = players.get(2);

        // when / then
        boolean allowed = ladderService.isChallengeAllowed(p2, p1);
        assertTrue(allowed);

        allowed = ladderService.isChallengeAllowed(p3, p2);
        assertTrue(allowed);
    }

    @Test
    public void shouldDenyInvalidChallenges() {
        // given
        List<Player> players = playerDao.readAll();
        Player p1 = players.get(0);
        Player p2 = players.get(1);
        Player p3 = players.get(2);

        // when / then
        boolean allowed = ladderService.isChallengeAllowed(p1, p2);
        assertFalse(allowed);

        allowed = ladderService.isChallengeAllowed(p2, p3);
        assertFalse(allowed);

        allowed = ladderService.isChallengeAllowed(p1, p3);
        assertFalse(allowed);

        allowed = ladderService.isChallengeAllowed(p3, p1);
        assertFalse(allowed);

        allowed = ladderService.isChallengeAllowed(p1, p1);
        assertFalse(allowed);
    }

    @Test
    public void shouldHandleValidMatchResult() {
        // GIVEN
        Ladder ladder = ladderDao.readAll().get(0);
        Player winner = ladder.getPlayer(3);
        Player loser = ladder.getPlayer(2);
        assertThat(ladder.getRank(winner), is(3));
        assertThat(ladder.getRank(loser), is(2));

        // WHEN
        ladderService.enterMatchResult(winner, loser);

        // THEN
        assertThat(ladder.getRank(winner), is(2));
        assertThat(ladder.getRank(loser), is(3));
    }

    @Test
    public void shouldDenyInvalidMatchResult() {
        // GIVEN
        Ladder ladder = ladderDao.readAll().get(0);
        Player winner = ladder.getPlayer(3);
        Player loser = ladder.getPlayer(1);

        // WHEN
        ladderService.enterMatchResult(winner, loser);
        
        // THEN
        assertThat(ladder.getRank(winner), is(3));
        assertThat(ladder.getRank(loser), is(1));
    }

    @Test
    public void shouldFindAllPossibleEnemies() {
        // GIVEN
        Ladder ladder = ladderDao.readAll().get(0);
        
        // WHEN / THEN
        List<Player> result = ladderService.getPossibleEnemies(ladder.getPlayer(1));
        assertThat(result.size(), is(0));

        result = ladderService.getPossibleEnemies(ladder.getPlayer(2));
        assertThat(result.size(), is(1));
        assertTrue(result.contains(ladder.getPlayer(1)));

        result = ladderService.getPossibleEnemies(ladder.getPlayer(3));
        assertThat(result.size(), is(1));
        assertTrue(result.contains(ladder.getPlayer(2)));

        result = ladderService.getPossibleEnemies(ladder.getPlayer(4));
        assertThat(result.size(), is(2));
        assertTrue(result.contains(ladder.getPlayer(3)));
        assertTrue(result.contains(ladder.getPlayer(2)));
    }

    @Test
    public void shouldCreateValidChallenge() {
        // GIVEN
        Ladder ladder = ladderDao.readAll().get(0);
        Player p = ladder.getPlayer(3);
        Player challenged = ladder.getPlayer(2);

        // WHEN
        ladderService.challenge(p, challenged);

        // THEN
        // no exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntCreateChallengeForSamePlayers() {
        // GIVEN
        Ladder ladder = ladderDao.readAll().get(0);
        Player p = ladder.getPlayer(3);

        // WHEN
        ladderService.challenge(p, p);

        // THEN
        // boom
    }
}