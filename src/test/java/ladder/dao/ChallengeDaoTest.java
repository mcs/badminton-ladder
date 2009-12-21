package ladder.dao;

import ladder.BaseTestFixture;
import ladder.model.Challenge;
import ladder.model.Ladder;
import ladder.model.Player;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ChallengeDaoTest extends BaseTestFixture {

    @Test
    public void shouldFindSavedChallenge() {
        // GIVEN
        Ladder l = ladderDao.readByPrimaryKey(1L);
        Player challenger = l.getPlayer(2);
        Player challenged = l.getPlayer(1);
        Challenge expected = new Challenge(challenger, challenged);
        challengeDao.save(expected);

        // WHEN
        Challenge result = challengeDao.findByChallengerAndChallenged(challenger, challenged);

        // THEN
        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldntFindUnsavedChallenge() {
        // GIVEN
        Ladder l = ladderDao.readByPrimaryKey(1L);
        Player challenger = l.getPlayer(2);
        Player challenged = l.getPlayer(1);

        // WHEN
        Challenge result = challengeDao.findByChallengerAndChallenged(challenger, challenged);

        // THEN
        assertThat(result, is(nullValue()));
    }
}
