package ladder.action;

import java.util.List;
import ladder.dao.LadderDao;
import ladder.model.Ladder;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class ShowLadderActionBean extends BaseActionBean {

    @SpringBean
    private LadderDao ladderDao;
    private Ladder ladder;

    public Ladder getLadder() {
        return ladder;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    @ValidationMethod
    public void populateAndCheckForLadder(ValidationErrors errors) {
        List<Ladder> ladders = ladderDao.findAll();
        if (ladders.isEmpty()) {
            errors.addGlobalError(new SimpleError("Keine Liga gefunden!"));
        }
        ladder = ladders.get(0);
    }

    @DefaultHandler
    public Resolution show() {
        return new ForwardResolution("/ladder.jsp");
    }
}
