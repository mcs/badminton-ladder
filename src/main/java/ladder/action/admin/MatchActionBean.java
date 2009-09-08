package ladder.action.admin;

import javax.annotation.security.RolesAllowed;
import ladder.action.BaseActionBean;
import ladder.action.LadderActionBean;
import ladder.dao.LadderDao;
import ladder.dao.PlayerDao;
import ladder.model.Ladder;
import ladder.model.Player;
import ladder.service.LadderService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StrictBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.util.Log;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@RolesAllowed("ENTER_RESULT")
@StrictBinding
public class MatchActionBean extends BaseActionBean {

    private static final Log log = Log.getInstance(MatchActionBean.class);

    @ValidateNestedProperties({
        @Validate(field = "id", required = true)
    })
    public Player winner;
    @ValidateNestedProperties({
        @Validate(field = "id", required = true)
    })
    public Player loser;
    @SpringBean
    private LadderDao ladderDao;
    @SpringBean
    private PlayerDao playerDao;
    @SpringBean
    private LadderService ladderService;
    private Ladder ladder;

    public Ladder getLadder() {
        return ladder;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation, LifecycleStage.CustomValidation})
    public void populate() {
        ladder = ladderDao.readAll().get(0);
    }

    @DefaultHandler
    @DontValidate
    public Resolution showEnterMatchView() {
        return new ForwardResolution(BASE_PATH + "/admin/enter_result.jsp");
    }

    @ValidationMethod(on = "setResult")
    public void validatePlayers(ValidationErrors errors) {
        if (winner.getId().equals(loser.getId())) {
            errors.add("loser.id", new SimpleError("Es müssen zwei unterschiedliche Spieler ausgewählt werden."));
        }
    }

    public Resolution setResult() {
        winner = playerDao.readByPrimaryKey(winner.getId());
        loser = playerDao.readByPrimaryKey(loser.getId());
        log.debug("alte Ladder: " + ladder.getPlayers());
        ladderService.enterMatchResult(winner, loser);
        log.debug("neue Ladder: " + ladder.getPlayers());
        return new RedirectResolution(LadderActionBean.class);
    }
}
