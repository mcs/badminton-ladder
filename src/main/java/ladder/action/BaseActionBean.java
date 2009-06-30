package ladder.action;

import ladder.stripesext.BadmintonActionBeanContext;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Base action bean using a custom action bean context.
 * @author Marcus Krassmann
 */
public class BaseActionBean implements ActionBean {

    public static final String BASE_PATH = "/WEB-INF/jsp";

    private BadmintonActionBeanContext ctx;

    @Override
    public void setContext(ActionBeanContext context) {
        this.ctx = (BadmintonActionBeanContext) context;
    }

    @Override
    public BadmintonActionBeanContext getContext() {
        return ctx;
    }
}
