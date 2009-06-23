package ladder.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

public class LoginActionBean extends BaseActionBean {

    @Validate(required = true)
    public String login, password;

    @DefaultHandler
    @DontValidate
    public Resolution showLoginPage() {
        return new ForwardResolution("/login.jsp");
    }

    public Resolution login() {
        return null;
    }
}
