package ladder.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import ladder.dao.PlayerDao;
import ladder.dao.UserDao;
import ladder.model.Player;
import ladder.model.User;
import ladder.stripesext.BadmintonActionBeanContext;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;

/**
 * Base action bean using a custom action bean context.
 * @author Marcus Krassmann
 */
public class BaseActionBean implements ActionBean {

    protected static final String BASE_PATH = "/WEB-INF/jsp";
    protected static final String ERRORS = "errors";
    protected Player selectedPlayer;
    @SpringBean
    protected PlayerDao playerDao;
    @SpringBean
    protected UserDao userDao;
    private BadmintonActionBeanContext ctx;

    @Override
    public void setContext(ActionBeanContext context) {
        this.ctx = (BadmintonActionBeanContext) context;
    }

    @Override
    public BadmintonActionBeanContext getContext() {
        return ctx;
    }

    @Before
    public void initSelectedPlayer() {
        if (selectedPlayer == null) {
            selectedPlayer = getContext().getPlayer();
        }
    }

    public Resolution changeSelectedPlayer() {
        selectedPlayer = playerDao.readByPrimaryKey(selectedPlayer.getId());
        User user = userDao.findByLogin(getContext().getUser().getLogin());
        if (selectedPlayer == null || !user.getPlayers().contains(selectedPlayer)) {
            // error
            getContext().getValidationErrors().addGlobalError(new SimpleError("Ungültiger Spieler"));
        } else if (selectedPlayer.equals(getContext().getPlayer())) {
            // error
            getContext().getValidationErrors().addGlobalError(new SimpleError("Spieler bereits ausgewählt"));
        } else {
            // OK
            getContext().setPlayer(selectedPlayer);
            getContext().getMessages().add(new SimpleMessage(("Spieler {0} erfolgreich ausgewählt"), selectedPlayer.getName()));
            return new RedirectResolution(IndexActionBean.class).flash(this);
        }
        return getContext().getSourcePageResolution();
    }

    public String getLastUrl() {
        HttpServletRequest req = getContext().getRequest();
        StringBuilder sb = new StringBuilder();

        // Start with the URI and the path
        String uri = (String) req.getAttribute("javax.servlet.forward.request_uri");
        String path = (String) req.getAttribute("javax.servlet.forward.path_info");
        if (uri == null) {
            uri = req.getRequestURI();
            path = req.getPathInfo();
        }
        sb.append(uri);
        if (path != null) {
            sb.append(path);
        }

        // Now the request parameters
        sb.append('?');
        @SuppressWarnings("unchecked")
        Map<String, String[]> map = new HashMap<String, String[]>(req.getParameterMap());

        // Append the parameters to the URL
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            for (String value : values) {
                sb.append(key).append('=').append(value).append('&');
            }
        }
        // Remove the last '&', or the '?' if no parameters exist
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }
}
