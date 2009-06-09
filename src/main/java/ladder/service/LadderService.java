package ladder.service;

import ladder.model.Ladder;
import ladder.model.Player;

/**
 *
 * @author Marcus Krassmann
 */
public interface LadderService {

    void rankPlayer(Ladder ladder, Player p, int newRank);
}
