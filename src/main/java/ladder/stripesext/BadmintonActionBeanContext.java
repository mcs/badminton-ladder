package ladder.stripesext;

import ladder.model.Ladder;
import ladder.model.User;
import net.sourceforge.stripes.action.ActionBeanContext;

public class BadmintonActionBeanContext extends ActionBeanContext {

    private static final String SESSION_USER = "user";
    private static final String SESSION_LADDER = "ladder";

    public User getUser() {
        return (User) getRequest().getSession().getAttribute(SESSION_USER);
    }

    public void setUser(User user) {
        getRequest().getSession().setAttribute(SESSION_USER, user);
    }

    public Ladder getLadder() {
        return (Ladder) getRequest().getSession().getAttribute(SESSION_LADDER);
    }

    public void setLadder(Ladder ladder) {
        getRequest().getSession().setAttribute(SESSION_LADDER, ladder);
    }

    public void invalidate() {
        getRequest().getSession().invalidate();
    }
}
