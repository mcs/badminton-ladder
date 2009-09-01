package ladder.stripesext;

import java.lang.reflect.Method;
import ladder.model.Role;
import ladder.model.User;
import net.sourceforge.stripes.action.ActionBean;
import org.stripesstuff.plugin.security.InstanceBasedSecurityManager;

public class SecurityManager extends InstanceBasedSecurityManager {

    @Override
    protected Boolean hasRole(ActionBean bean, Method handler, String role) {
        BadmintonActionBeanContext ctx = (BadmintonActionBeanContext) bean.getContext();
        User user = ctx.getUser();
        return user != null && new Role(role).equals(user.getRole());
    }

    @Override
    protected Boolean hasRoleName(ActionBean bean, Method handler, String roleName) {
        return super.hasRoleName(bean, handler, roleName);
    }

}
