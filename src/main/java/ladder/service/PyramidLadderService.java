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
    private PlayerDao playerDao;

    @Override
    public void enterMatchResult(long winner_id, long loser_id) {
        // init
        Player winner = playerDao.readByPrimaryKey(winner_id);
        Player loser = playerDao.readByPrimaryKey(loser_id);
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
    public boolean isChallengeAllowed(long challenger_id, long challenged_id) {
        // init
        Player challenger = playerDao.readByPrimaryKey(challenger_id);
        Player challenged = playerDao.readByPrimaryKey(challenged_id);
        return PyramidUtil.isChallengeAllowed(challenger, challenged);
    }
}
