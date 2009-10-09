package ladder.service;

public interface LadderService {

    void enterMatchResult(long winner_id, long loser_id);

    public boolean isChallengeAllowed(long player1_id, long player2_id);
}
