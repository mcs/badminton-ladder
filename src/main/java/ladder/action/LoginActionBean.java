package ladder.action;

import ladder.model.User;
import ladder.service.UserService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class LoginActionBean extends BaseActionBean {

    @Validate(required = true)
    public String username, password;

    @SpringBean
    private UserService userService;
    private User user;

    @DefaultHandler
    @DontValidate
    public Resolution showLoginPage() {
        return new ForwardResolution(BASE_PATH + "/login.jsp");
    }

    @ValidationMethod(on = "login")
    public void tryLogin(ValidationErrors errors) {
        user = userService.login(username, password);
        if (user == null) {
            errors.addGlobalError(new SimpleError("Login nicht gültig"));
        }
    }

    public Resolution login() {
        getContext().setUser(user);
        getContext().getMessages().add(new SimpleMessage("Willkommen, {0}", user.getLogin()));
        return new RedirectResolution(IndexActionBean.class).flash(this);
    }
}
