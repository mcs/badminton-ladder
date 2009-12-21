package ladder.action;

import javax.annotation.security.PermitAll;
import ladder.model.Player;
import ladder.service.LadderService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontBind;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.Wizard;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * Handles challenging other players.
 */
@Wizard(startEvents = {"doNothing", "step1"})
@PermitAll
public class ChallengeActionBean extends BaseActionBean {

    @ValidateNestedProperties({
        @Validate(field = "id", required = true)
    })
    private Player challenged;
    @SpringBean
    private LadderService ladderService;

    /**
     * Refreshes both players from database.
     */
    @Before(on = "!doNothing")
    public void initPlayers() {
        getContext().setPlayer(playerDao.readByPrimaryKey(getContext().getPlayer().getId()));
        challenged = playerDao.readByPrimaryKey(challenged.getId());
    }

    /**
     * Hook event for <tt>&lt;s:useActionBean&gt;</tt>.
     * @return <tt>null</tt>
     */
    @DefaultHandler
    @DontValidate
    @DontBind
    public Resolution doNothing() {
        return null;
    }

    /**
     * Always check if the challenge is still allowed.
     */
    @ValidationMethod
    public void checkForAllowedChallenge(ValidationErrors errors) {
        ladderService.isChallengeAllowed(getContext().getPlayer(), challenged);
    }

    /**
     * Switches to the challenge mask (if the passed challenged is valid for the
     * challenger).
     * @return the first challenge mask
     */
    public Resolution step1() {
        return new ForwardResolution(BASE_PATH + "/challenge/step1.jsp");
    }

    /**
     * Confirms the challenge and goes back to the ladder view.
     * @return the ladder view
     */
    public Resolution confirm() {
        ladderService.challenge(getContext().getPlayer(), challenged);
        getContext().getMessages().add(new SimpleMessage("{0} wurde von Ihnen herausgefordert.", challenged.getName()));
        return new RedirectResolution(LadderActionBean.class);
    }

    /**
     * Aborts the challenge and goes back to the ladder view.
     * @return the ladder view
     */
    public Resolution abort() {
        getContext().getMessages(ERRORS).add(new SimpleMessage("{0} wird nicht herausgefordert.", challenged.getName()));
        return new RedirectResolution(LadderActionBean.class);
    }

    public Player getChallenged() {
        return challenged;
    }

    public void setChallenged(Player challenged) {
        this.challenged = challenged;
    }
}
