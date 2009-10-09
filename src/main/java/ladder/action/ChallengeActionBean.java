package ladder.action;

import javax.annotation.security.RolesAllowed;
import ladder.dao.PlayerDao;
import ladder.model.Player;
import ladder.service.ChallengeService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.Wizard;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

@RolesAllowed("CREATE_CHALLENGE")
@Wizard(startEvents = "doNothing")
public class ChallengeActionBean extends BaseActionBean {

    @ValidateNestedProperties({
        @Validate(field = "id", required = true, minvalue = 1)
    })
    private Player challenged;
    @SpringBean
    private PlayerDao playerDao;
    @SpringBean
    private ChallengeService challengeService;

    public void initChallenged() {
        challenged = playerDao.readByPrimaryKey(challenged.getId());
    }

    @DefaultHandler
    public Resolution doNothing() {
        // should never be called directly by a user
        return new RedirectResolution(IndexActionBean.class);
    }

    public Resolution showConfirm() {
        if (challenged != null) {
            // first check okay, show confirm dialog
            return new ForwardResolution(BASE_PATH + "/player/challenge.jsp");
        }
        // invalid player.id passed to bean, create error
        getContext().getValidationErrors().addGlobalError(new SimpleError("Die Herausforderung konnte nicht angelegt werden. Der Administrator wurde informiert."));
        return getContext().getSourcePageResolution();
    }

    public Resolution yes() {
        challengeService.challenge(getPlayer().getId(), challenged.getId());
        return new RedirectResolution(IndexActionBean.class);
        
    }

    public Resolution no() {
        getContext().getValidationErrors().addGlobalError(new SimpleError("Die Herausforderung wurde nicht angelegt."));
        return new RedirectResolution(IndexActionBean.class);
    }

    public Player getChallenged() {
        return challenged;
    }

    public void setChallenged(Player challenged) {
        this.challenged = challenged;
    }
}
