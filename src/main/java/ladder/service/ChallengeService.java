package ladder.service;

import ladder.dao.ChallengeDao;
import ladder.dao.PlayerDao;
import ladder.model.Challenge;
import ladder.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChallengeService {

    private PlayerDao playerDao;
    private ChallengeDao challengeDao;

    @Autowired
    public void setChallengeDao(ChallengeDao challengeDao) {
        this.challengeDao = challengeDao;
    }

    @Autowired
    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    private boolean challengeExists(Challenge c) {
        return challengeDao.readByExample(c) != null;
    }

    public void challenge(long challenger_id, long challenged_id) {
        Player challenger = playerDao.readByPrimaryKey(challenger_id);
        Player challenged = playerDao.readByPrimaryKey(challenged_id);
        if (challenger.getLadder().equals(challenged.getLadder())) {
            Challenge c = new Challenge();
            c.setChallenger(challenger);
            c.setChallenged(challenged);
            if (challenger != null && challenged != null && !challengeExists(c)) {
                challengeDao.save(c);
            } else {
                throw new RuntimeException("Challenge already exists.");
            }

        }
    }
}
