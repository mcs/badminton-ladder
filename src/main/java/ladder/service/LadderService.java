package ladder.service;

import ladder.model.Player;

public interface LadderService {

    void enterMatchResult(Player winner, Player loser);

    public boolean isChallengeAllowed(Player player1, Player player2);
}
