package ladder.stripesext;

import ladder.model.User;
import net.sourceforge.stripes.action.ActionBeanContext;

public class BadmintonActionBeanContext extends ActionBeanContext {

    private static final String SESSION_USER = "user";

    public User getUser() {
        return (User) getRequest().getSession().getAttribute(SESSION_USER);
    }

    public void setUser(User user) {
        getRequest().getSession().setAttribute(SESSION_USER, user);
    }

    public void invalidate() {
        getRequest().getSession().invalidate();
    }
}
