package ladder.dao;

import ladder.model.Challenge;
import ladder.model.Player;
import org.synyx.hades.dao.ExtendedGenericDao;

/**
 * Type-safe returning challenges.
 */
public interface ChallengeDao extends ExtendedGenericDao<Challenge, Long> {

    public Challenge findByChallengerAndChallenged(Player challenger, Player challenged);
}
