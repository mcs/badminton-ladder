package ladder.action;

import java.util.List;
import ladder.dao.PlayerDao;
import ladder.model.Player;
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
    private User user;
    @SpringBean
    private UserService userService;

    @DefaultHandler
    @DontValidate
    public Resolution showLoginPage() {
        return new ForwardResolution(BASE_PATH + "/login.jsp");
    }

    @ValidationMethod(on = "login")
    public void tryLogin(ValidationErrors errors) {
    }

    public Resolution login() {
        user = userService.login(username, password);
        if (user != null) {
            // success
            // store user in context
            getContext().setUser(user);
            // load all players belonging to this user and store them in the context
            List<Player> players = playerDao.findByUser(user);
            getContext().setPlayers(players);
            // choose the first player, if one exists
            if (!players.isEmpty())
                getContext().setPlayer(players.get(0));
            // create welcome message
            getContext().getMessages().add(new SimpleMessage("Willkommen, {0}", user.getLogin()));
            return new RedirectResolution(IndexActionBean.class);
        }
        // failure (probably login invalid)
        getContext().getValidationErrors().addGlobalError(new SimpleError("Der Login ist nicht gültig"));
        return getContext().getSourcePageResolution();
    }
}
