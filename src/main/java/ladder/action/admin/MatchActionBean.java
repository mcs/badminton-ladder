package ladder.action.admin;

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
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@StrictBinding
public class MatchActionBean extends BaseActionBean {

    @ValidateNestedProperties({
        @Validate(field = "id", required = true)
    })
    public Player winner;
    @ValidateNestedProperties({
        @Validate(field = "id", required = true)
    })
    public Player loser;
    @SpringBean
    private PlayerDao playerDao;
    @SpringBean
    private LadderDao ladderDao;
    @SpringBean
    private LadderService ladderService;
    private Ladder ladder;

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
    @DontValidate
    public Resolution showEnterMatchView() {
        return new ForwardResolution("/admin/enter_result.jsp");
    }

    @Before(on = "setResult", stages = LifecycleStage.CustomValidation)
    public void repopulateLadder() {
        ladder = ladderDao.readAll().get(0);
    }

    @ValidationMethod(on = "setResult")
    public void validatePlayers(ValidationErrors errors) {
        winner = playerDao.readByPrimaryKey(winner.getId());
        loser = playerDao.readByPrimaryKey(loser.getId());
        if (winner.getId().equals(loser.getId())) {
            errors.add("loser.id", new SimpleError("Es müssen zwei unterschiedliche Spieler ausgewählt werden."));
        }
    }

    public Resolution setResult() {
        ladderService.enterMatchResult(winner, loser);
        return new RedirectResolution(LadderActionBean.class);
    }
}
