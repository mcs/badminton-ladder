package ladder.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class IndexActionBean extends BaseActionBean {

    @DefaultHandler
    public Resolution index() {
        return new ForwardResolution("/index.jsp");
    }
}
