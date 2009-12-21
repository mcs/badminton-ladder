package ladder.service;

import java.util.List;
import ladder.model.Player;

public interface LadderService {

    void challenge(Player challenger, Player challenged);

    boolean isChallengeAllowed(Player challenger, Player challenged);

    void enterMatchResult(Player winner, Player loser);

    List<Player> getPossibleEnemies(Player p);
}
