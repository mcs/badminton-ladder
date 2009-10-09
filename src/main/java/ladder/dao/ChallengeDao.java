package ladder.dao;

import ladder.model.Challenge;
import org.synyx.hades.dao.ExtendedGenericDao;

/**
 * Type-safe returning challenges.
 */
public interface ChallengeDao extends ExtendedGenericDao<Challenge, Long> {

}
