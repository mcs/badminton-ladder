package ladder.action.admin;

import javax.annotation.security.RolesAllowed;
import ladder.action.BaseActionBean;
import ladder.dao.LadderDao;
import ladder.dao.PlayerDao;
import ladder.model.Ladder;
import ladder.model.Player;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StrictBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.util.Log;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

@RolesAllowed("EDIT_LADDER")
@StrictBinding
public class PlayerActionBean extends BaseActionBean {

    private static final Log log = Log.getInstance(PlayerActionBean.class);
    @ValidateNestedProperties({
        @Validate(field = "id", required = true)
    })
    private Player player;
    private Ladder ladder;
    @SpringBean
    private LadderDao ladderDao;

    public Ladder getLadder() {
        return ladder;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation, LifecycleStage.CustomValidation})
    public void populate() {
        if (ladder != null) {
            ladder = ladderDao.readByPrimaryKey(ladder.getId());
        }
        if (player != null) {
            player = playerDao.readByPrimaryKey(player.getId());
        }
    }

    @DefaultHandler
    public Resolution addPlayer() {
        return new ForwardResolution(BASE_PATH + "/admin/add_player.jsp");
    }
}
