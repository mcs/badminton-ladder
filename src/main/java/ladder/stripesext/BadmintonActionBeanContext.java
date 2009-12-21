package ladder.stripesext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import ladder.model.Player;
import ladder.model.User;
import net.sourceforge.stripes.action.ActionBeanContext;

public class BadmintonActionBeanContext extends ActionBeanContext {

    private static final String SESSION_USER = "user";
    private static final String SESSION_PLAYER = "player";
    private static final String SESSION_PLAYERLIST = "players";

    public User getUser() {
        return (User) getRequest().getSession().getAttribute(SESSION_USER);
    }

    public void setUser(User user) {
        getRequest().getSession().setAttribute(SESSION_USER, user);
    }

    public Player getPlayer() {
        return (Player) getRequest().getSession().getAttribute(SESSION_PLAYER);
    }

    public void setPlayer(Player player) {
        getRequest().getSession().setAttribute(SESSION_PLAYER, player);
    }

    @SuppressWarnings("unchecked")
    public List<Player> getPlayers() {
        List<Player> players = (List<Player>) getRequest().getSession().getAttribute(SESSION_PLAYERLIST);
        return players;
    }

    public void setPlayers(List<Player> players) {
        getRequest().getSession().setAttribute(SESSION_PLAYERLIST, players);
    }

    public void invalidate() {
        getRequest().getSession().invalidate();
    }

    public String getLastUrl() {
        String sourcePage = getSourcePage();
        if (sourcePage != null) {
            StringBuilder lastUrl = new StringBuilder();
            HttpServletRequest request = getRequest();
            lastUrl.append(request.getRequestURL().toString());
            lastUrl.append("?");
            Enumeration enu = request.getParameterNames();
            try {
                while (enu.hasMoreElements()) {
                    String key = (String) enu.nextElement();
                    String[] values = request.getParameterValues(key);
                    if (values.length == 1) {
                        lastUrl.append(encodeUTF8(key));
                        lastUrl.append("=");
                        lastUrl.append(encodeUTF8(values[0]));
                        lastUrl.append("&");
                    } else {
                        for (int i = 0; i < values.length; i++) {
                            lastUrl.append(encodeUTF8(key)).append("[").append(i).append("]");
                            lastUrl.append("=");
                            lastUrl.append(encodeUTF8(values[i]));
                            lastUrl.append("&");
                        }
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException("Encoding not supported: UTF-8", ex);
            }
            int size = lastUrl.length();
            // remove last '&' or '?'
            lastUrl.delete(size - 1, size);
            return lastUrl.toString();
        }
        return null;
    }

    private String encodeUTF8(String key) throws UnsupportedEncodingException {
        return URLEncoder.encode(key, "UTF-8");
    }
}
