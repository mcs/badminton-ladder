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
import net.sourceforge.stripes.validation.SimpleError;
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
            if (!isUserAuthenticated(bean, handler)) {
                RedirectResolution resolution = new RedirectResolution(LoginActionBean.class);
                if (bean.getContext().getRequest().getMethod().equalsIgnoreCase("GET")) {
                    String loginUrl = ((BaseActionBean) bean).getLastUrl();
                    resolution.addParameter("loginUrl", loginUrl);
                }
                return resolution;
            }
            return new ErrorResolution(HttpServletResponse.SC_UNAUTHORIZED);

        }
        Resolution spr = bean.getContext().getSourcePageResolution();
        bean.getContext().getMessages("errors").add(new SimpleError("No access allowed. Please login with needed access level."));
        if (spr == null) {
            return new RedirectResolution("/").flash(bean);
        } else {
            return new ErrorResolution(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private User getUser(ActionBean bean) {
        return ((BaseActionBean) bean).getContext().getUser();
    }
}
