package ladder.action.admin;

import java.util.List;
import ladder.action.BaseActionBean;
import ladder.dao.LadderDao;
import ladder.model.Ladder;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

public class LadderActionBean extends BaseActionBean {

    @SpringBean
    private LadderDao ladderDao;

    private List<Ladder> ladders;

    public LadderDao getLadderDao() {
        return ladderDao;
    }

    public void setLadderDao(LadderDao ladderDao) {
        this.ladderDao = ladderDao;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    @DefaultHandler
    public Resolution showAllLadders() {
        ladders = ladderDao.findAll();
        return new ForwardResolution("/admin/ladders.jsp");
    }
}
