package ladder.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;

public class LogoutActionBean extends BaseActionBean {

    @DefaultHandler
    @DontValidate
    public Resolution logout() {
        getContext().invalidate();
        getContext().getMessages().add(new SimpleMessage("Sie wurden erfolgreich abgemeldet."));
        return new RedirectResolution(IndexActionBean.class).flash(this);
    }
}
