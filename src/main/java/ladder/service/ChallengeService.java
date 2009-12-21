package ladder.service;

import ladder.model.Player;

/**
 * Offers services for challenging players.
 * @author Marcus Krassmann
 */
public interface ChallengeService {

    /**
     * Checks if a player would be allowed to challenge another one.
     * @param challenger the challenger
     * @param challenged the challenged
     * @return <tt>true</tt> if the challenge would be okay
     */
    public boolean isAllowed(Player challenger, Player challenged);

}
