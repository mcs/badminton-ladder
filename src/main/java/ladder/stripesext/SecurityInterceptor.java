package ladder.stripesext;

import java.lang.reflect.Method;
import org.stripesstuff.plugin.security.SecurityHandler;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.config.BootstrapPropertyResolver;
import net.sourceforge.stripes.config.ConfigurableComponent;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.exception.StripesRuntimeException;
import net.sourceforge.stripes.util.Log;

/**
 * Security interceptor for the Stripes framework. Determines if handling the event for the current execution context
 * is allowed. Execution is allowed if there is no security manager, or if the security manager allows it. See the
 * documentation of the SecurityManager interface for more information.
 *
 * @author <a href="mailto:kindop@xs4all.nl">Oscar Westra van Holthe - Kind</a>
 * @author <a href="mailto:xf2697@fastmail.fm">Fred Daoud</a>
 * @version $Id: SecurityInterceptor.java 203 2007-04-27 18:42:44Z oscar $
 * @see SecurityManager
 * @see SecurityHandler
 */
@Intercepts({LifecycleStage.HandlerResolution})
public class SecurityInterceptor implements Interceptor, ConfigurableComponent {

    /**
     * Key used to lookup the name of the SecurityManager class in the Stripes configuration.
     * This class must have a no-arg constructor and implement StipesSecurityManager.
     */
    public static final String SECURITY_MANAGER_CLASS = "SecurityManager.Class";
    /**
     * Key used to store the security manager in the request before processing resolutions.
     */
    public static final String SECURITY_MANAGER = java.lang.SecurityManager.class.getName();
    /**
     * Logger for this class.
     */
    private static final Log LOG = Log.getInstance(SecurityInterceptor.class);
    /**
     * The configured security manager.
     */
    private SecurityManager securityManager;

    /**
     * Initialize the interceptor.
     *
     * @param configuration the configuration being used by Stripes
     * @throws StripesRuntimeException if the security manager cannot be created
     */
    @Override
    public void init(Configuration configuration)
            throws StripesRuntimeException {
        BootstrapPropertyResolver resolver = configuration.getBootstrapPropertyResolver();

        // Instantiate the security manager.

        try {
            // Ask the BootstrapPropertyResolver for a subclass of SecurityManager.
            // BootstrapPropertyResolver will look in web.xml first then scan the
            // classpath if the class wasn't specified in web.xml

            Class<? extends SecurityManager> clazz = resolver.getClassProperty(SECURITY_MANAGER_CLASS, SecurityManager.class);

            if (clazz != null) {
                securityManager = (SecurityManager) clazz.newInstance();
            }
        } catch (Exception e) {
            String msg = "Failed to configure the SecurityManager: instantiation failed.";
            throw new StripesRuntimeException(msg, e);
        }

        if (securityManager != null) {
            LOG.info("Initialized the SecurityInterceptor with the SecurityManager: " + securityManager.toString());
        } else {
            LOG.info("Initialized the SecurityInterceptor without a SecurityManager (all access will be allowed).");
        }
    }

    /**
     * Intercept execution.
     *
     * @param executionContext the context of the execution being intercepted
     * @return the resulting {@link Resolution}; returns {@link ExecutionContext#proceed()} if all is well
     * @throws Exception on error
     */
    @Override
    public Resolution intercept(ExecutionContext executionContext) throws Exception {
        Resolution resolution;

        if (securityManager != null) {
            resolution = interceptHandlerResolution(executionContext);
        } else {
            // There is no security manager, so everything is allowed.
            resolution = executionContext.proceed();
        }

        return resolution;
    }

    /**
     * Intercept execution after handler resolution.
     *
     * @param executionContext the context of the execution being intercepted
     * @return the resulting {@link net.sourceforge.stripes.action.Resolution}; returns {@link ExecutionContext#proceed()} if all is well
     * @throws Exception on error
     */
    protected Resolution interceptHandlerResolution(ExecutionContext executionContext) throws Exception {
        Resolution resolution = executionContext.proceed();

        if (resolution != null && Boolean.FALSE.equals(getAccessAllowed(executionContext))) {
            // If the security manager denies access, deny access.

            LOG.debug("Binding and/or validation failed, and the security manager has denied access.");
            resolution = handleAccessDenied(executionContext.getActionBean(), executionContext.getHandler());
        }

        return resolution;
    }

    /**
     * Determine if the security manager allows access.
     * The return value of this method is the same as the result of
     * {@link SecurityManager#getAccessAllowed(ActionBean,Method) getAccessAllowed(ActionBean, Method)}
     * of the current security manager, unless there is nu security manager (in which case the event is allowed).
     *
     * @param executionContext the current execution context
     * @return whether or not the security manager allows access, if a decision can be made
     */
    protected Boolean getAccessAllowed(ExecutionContext executionContext) {
        LOG.debug("Checking access for " + executionContext + " at " + executionContext.getLifecycleStage());

        Boolean accessAllowed;
        if (securityManager == null) {
            LOG.debug("There is no security manager, so access is allowed by default.");
            accessAllowed = true;
        } else {
            ActionBean actionBean = executionContext.getActionBean();
            Method handler = executionContext.getHandler();
            accessAllowed = securityManager.getAccessAllowed(actionBean, handler);
            LOG.debug("Security manager returned access allowed: " + accessAllowed);
        }

        return accessAllowed;
    }

    /**
     * Determine what to do when access has been denied. If the SecurityManager implements the optional interface
     * [@Link SecurityHandler}, ask the SecurityManager. Otherwise, return the HTTP error "forbidden".
     *
     * @param bean    the action bean to which access was denied
     * @param handler the event handler to which access was denied
     * @return the Resolution to be executed when access has been denied
     */
    protected Resolution handleAccessDenied(ActionBean bean, Method handler) {
        Resolution resolution;
        if (securityManager == null) {
            resolution = new ErrorResolution(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            resolution = securityManager.handleAccessDenied(bean, handler);
        }
        return resolution;
    }
}
