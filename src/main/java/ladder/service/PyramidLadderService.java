package ladder.service;

import java.util.ArrayList;
import java.util.List;
import ladder.dao.ChallengeDao;
import ladder.dao.PlayerDao;
import ladder.model.Challenge;
import ladder.model.Ladder;
import ladder.model.Player;
import ladder.util.PyramidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PyramidLadderService implements LadderService {

    private ChallengeDao challengeDao;
    private PlayerDao playerDao;

    @Override
    public boolean isChallengeAllowed(Player challenger, Player challenged) {
        // preconditions
        if (challenger == null || challenged == null) {
            throw new IllegalArgumentException("At least one player was null!");
        }
        // init
        boolean allowed = PyramidUtil.isChallengeAllowed(challenger, challenged);
        allowed &= !challengeExists(new Challenge(challenger, challenged));
        return allowed;
    }

    @Override
    @Transactional
    public void challenge(Player challenger, Player challenged) {
        // preconditions
        if (!isChallengeAllowed(challenger, challenged)) {
            throw new IllegalArgumentException("Challenge not allowed!");
        }
        if (challenger.getLadder().equals(challenged.getLadder())) {
            // create the challenge
            Challenge c = new Challenge();
            c.setChallenger(challenger);
            c.setChallenged(challenged);
            if (!challengeExists(c)) {
                challengeDao.save(c);
            } else {
                throw new RuntimeException("Challenge already exists.");
            }
        }
    }

    @Override
    public void enterMatchResult(Player winner, Player loser) {
        // init
        if (PyramidUtil.isChallengeAllowed(winner, loser) || PyramidUtil.isChallengeAllowed(loser, winner)) {
            // logic
            Ladder ladder = winner.getLadder();
            int rankLoser = ladder.getRank(loser);
            int rankWinner = ladder.getRank(winner);
            if (rankLoser < rankWinner) {
                ladder.setRank(winner, rankLoser);
            }
        }
    }

    @Override
    public List<Player> getPossibleEnemies(Player p) {
        Ladder ladder = p.getLadder();
        List<Player> result = new ArrayList<Player>();
        for (Player enemy : ladder.getPlayers()) {
            if (PyramidUtil.isChallengeAllowed(p, enemy)) {
                result.add(enemy);
            }
        }
        return result;
    }

    private boolean challengeExists(Challenge c) {
        return !challengeDao.readByExample(c).isEmpty();
    }

    @Autowired
    protected void setChallengeDao(ChallengeDao challengeDao) {
        this.challengeDao = challengeDao;
    }

    @Autowired
    protected void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }
}
