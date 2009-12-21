package ladder.stripesext;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletResponse;
import ladder.action.BaseActionBean;
import ladder.action.LoginActionBean;
import ladder.model.Right;
import ladder.model.User;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.stripesstuff.plugin.security.InstanceBasedSecurityManager;
import org.stripesstuff.plugin.security.SecurityHandler;

public class SecurityManager extends InstanceBasedSecurityManager implements SecurityHandler {

    @Override
    protected Boolean isUserAuthenticated(ActionBean bean, Method handler) {
        return getUser(bean) != null;
    }

    @Override
    protected Boolean hasRoleName(ActionBean bean, Method handler, String right) {
        User user = getUser(bean);
        return user != null && user.hasRight(new Right(right));
    }

    @Override
    public Resolution handleAccessDenied(ActionBean bean, Method handler) {
        if (!isUserAuthenticated(bean, handler)) {
            // no user is logged in, redirect to login page
            RedirectResolution resolution = new RedirectResolution(LoginActionBean.class);
            if (bean.getContext().getRequest().getMethod().equalsIgnoreCase("GET")) {
                // append origin to the login url
                String loginUrl = ((BaseActionBean) bean).getLastUrl();
                resolution.addParameter("loginUrl", loginUrl);
            }
            return resolution;
        }
        // User is authenticated, but has not the necessary right
        return new ErrorResolution(HttpServletResponse.SC_UNAUTHORIZED);
    }

    private User getUser(ActionBean bean) {
        return ((BaseActionBean) bean).getContext().getUser();
    }
}
