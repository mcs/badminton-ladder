package ladder.service;

import ladder.dao.LadderDao;
import ladder.dao.PlayerDao;
import ladder.model.Ladder;
import ladder.model.Player;
import ladder.util.PyramidUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PyramidLadderService implements LadderService {

    private static final Log log = LogFactory.getLog(PyramidLadderService.class);
    @Autowired
    private LadderDao ladderDao;
    @Autowired
    private PlayerDao playerDao;

    @Override
    public void enterMatchResult(Player winner, Player loser) {
        // init
        Ladder ladder = ladderDao.readAll().get(0);

        // logic
        int rankLoser = ladder.getRank(loser);
        int rankWinner = ladder.getRank(winner);
        if (rankLoser < rankWinner) {
            ladder.setRank(winner, rankLoser);
        }
    }

    @Override
    public boolean isChallengeAllowed(Player challenger, Player challenged) {
        // init
        Ladder ladder = ladderDao.readAll().get(0);
        challenger = playerDao.readByPrimaryKey(challenger.getId());
        challenged = playerDao.readByPrimaryKey(challenged.getId());
        try {

            // logic
            int rankChallenger = ladder.getRank(challenger);
            int rankChallenged = ladder.getRank(challenged);
            if (rankChallenged > rankChallenger) {
                return false;
            }

            int rowChallenger = PyramidUtil.getRowForRank(rankChallenger);
            return rankChallenger - rowChallenger < rankChallenged;
        } catch (IllegalArgumentException e) {
            log.warn("Player not in ladder", e);
            return false;
        }
    }
}
