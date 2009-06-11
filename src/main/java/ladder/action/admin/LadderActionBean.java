package ladder.action.admin;

import java.util.List;
import ladder.action.BaseActionBean;
import ladder.dao.LadderDao;
import ladder.model.Ladder;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

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

    @ValidationMethod
    public void populateAndCheckForLadder(ValidationErrors errors) {
        ladders = ladderDao.findAll();
        if (ladders.isEmpty()) {
            errors.addGlobalError(new SimpleError("Keine Liga gefunden!"));
        }
    }

    @DefaultHandler
    public Resolution showAllLadders() {
        return new ForwardResolution("/admin/ladders.jsp");
    }
}
