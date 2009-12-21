package ladder.action;

import java.util.Collection;
import ladder.dao.LadderDao;
import ladder.model.Ladder;
import ladder.model.Player;
import ladder.service.LadderService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.synyx.hades.domain.Sort;

public class LadderActionBean extends BaseActionBean {

    @SpringBean
    private LadderDao ladderDao;
    @SpringBean
    private LadderService ladderService;

    private Ladder ladder;
    private Collection<Player> possibleEnemies;

    @Before
    public void populateLadder() {
        Player p = getContext().getPlayer();
        if (p == null) {
            ladder = ladderDao.readAll(new Sort("id")).get(0);
        } else {
            p = playerDao.readByPrimaryKey(p.getId());
            ladder = ladderDao.readByPrimaryKey(p.getLadder().getId());
        }
    }

    @DefaultHandler
    public Resolution showLadders() {
        Player p = getContext().getPlayer();
        if (p != null) {
            possibleEnemies = ladderService.getPossibleEnemies(p);
        }
        return new ForwardResolution(BASE_PATH + "/ladder.jsp");
    }

    public Ladder getLadder() {
        return ladder;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    public Collection<Player> getPossibleEnemies() {
        return possibleEnemies;
    }
}
