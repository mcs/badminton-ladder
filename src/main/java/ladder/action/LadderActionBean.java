package ladder.action;

import ladder.dao.LadderDao;
import ladder.model.Ladder;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

public class LadderActionBean extends BaseActionBean {

    @SpringBean
    private LadderDao ladderDao;

    private Ladder ladder;

    public LadderDao getLadderDao() {
        return ladderDao;
    }

    public void setLadderDao(LadderDao ladderDao) {
        this.ladderDao = ladderDao;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    @Before
    public void populateLadder() {
        ladder = ladderDao.readAll().get(0);
    }

    @DefaultHandler
    public Resolution showLadders() {
        return new ForwardResolution(BASE_PATH + "/ladder.jsp");
    }
}
