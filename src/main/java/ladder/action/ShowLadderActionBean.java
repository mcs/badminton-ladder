package ladder.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class ShowLadderActionBean extends BaseActionBean {

    @DefaultHandler
    public Resolution show() {
        return new ForwardResolution("/ladder.jsp");
    }
}
