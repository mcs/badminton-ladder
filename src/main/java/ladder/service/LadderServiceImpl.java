package ladder.service;

import ladder.dao.LadderDao;
import ladder.dao.PlayerDao;
import ladder.model.Ladder;
import ladder.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class LadderServiceImpl implements LadderService {

    private LadderDao ladderDao;
    private PlayerDao playerDao;

    @Autowired
    public void setLadderDao(LadderDao ladderDao) {
        this.ladderDao = ladderDao;
    }

    @Autowired
    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    @Transactional
    public void rankPlayer(Ladder ladder, Player p, int newRank) {
        ladder = ladderDao.findById(ladder.getId());
        p = playerDao.findById(p.getId());
        ladder.setRank(p, newRank);
    }

}
