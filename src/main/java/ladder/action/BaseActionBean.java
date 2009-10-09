package ladder.action;

import ladder.dao.PlayerDao;
import ladder.model.Player;
import ladder.model.User;
import ladder.stripesext.BadmintonActionBeanContext;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 * Base action bean using a custom action bean context.
 * @author Marcus Krassmann
 */
public class BaseActionBean implements ActionBean {

    public static final String BASE_PATH = "/WEB-INF/jsp";

    private BadmintonActionBeanContext ctx;
    @SpringBean
    private PlayerDao playerDao;

    @Override
    public void setContext(ActionBeanContext context) {
        this.ctx = (BadmintonActionBeanContext) context;
    }

    @Override
    public BadmintonActionBeanContext getContext() {
        return ctx;
    }

    public Resolution changeSelectedPlayer() {
        return new RedirectResolution(IndexActionBean.class);
    }

    public User getUser() {
        return getContext().getUser();
    }

    public Player getPlayer() {
        return ctx.getPlayer();
    }

    public void setPlayer(Player player) {
        ctx.setPlayer(playerDao.readByPrimaryKey(player.getId()));
    }

}
