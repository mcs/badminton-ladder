package ladder.exception;

import java.util.Arrays;

/**
 * Intended to be thrown in business classes if some business validation rules
 * are violated.
 * <p/>
 * Usually, in a MVC system, simple validation of input parameters is handled
 * by controller classes. But checking complex rules of the business layer
 * should be done in this separate layer. By throwing this exception and
 * having a well configured exception handler in your MVC environment, you can
 * keep your controller classes small and clean, without any dirt from
 * lower layers.
 * <h3>Usage:</h3>
 * Just throw this exception in your business layer's code like this:
 * <pre>
 * if (user != null) {
 *     throw new LocalizableException("error.user.exists", user.getUsername());
 * }
 * </pre>
 * This implies that you have a resource bundle with an entry like this:
 * <pre>
 * error.user.exists=The username {0} already exists. Please choose another one.
 * </pre>
 * Remember that it depends on your MVC framework what placeholder number you
 * have to use in your resource bundle. Ensure that your exception handler
 * catches this exception, grabs the parameters and creates a localized error
 * message for the view.
 *
 * @author Marcus Kraßmann
 */
public class LocalizableException extends RuntimeException {

    private String resourceKey;
    private Object[] args;

    /**
     * Constructs a new Exception with the specified resource key and optional arguments.
     * These parameters may be used to lookup a localized error message.
     * @param resourceKey the resource key
     * @param args optional arguments
     */
    public LocalizableException(String resourceKey, Object... args) {
        this.resourceKey = resourceKey;
        this.args = args;
    }

    /**
     * Get the resource key passed with this exception.
     * @return the resource key
     */
    public String getResourceKey() {
        return resourceKey;
    }

    /**
     * Get the arguments passed with the exception.
     * @return optional arguments (may be <tt>null</tt>)
     */
    public Object[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        String message = String.format("%s [%s]", resourceKey, Arrays.toString(args));
        return super.toString() + " | " + message;
    }
}
