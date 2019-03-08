package org.apache.shiro.authc;

/**
 * 超过认证次数异常
 */
public class ExcessiveAttemptsException extends AccountException {

    /**
     * Creates a new ExcessiveAttemptsException.
     */
    public ExcessiveAttemptsException() {
        super();
    }

    /**
     * Constructs a new ExcessiveAttemptsException.
     *
     * @param message the reason for the exception
     */
    public ExcessiveAttemptsException(String message) {
        super(message);
    }

    /**
     * Constructs a new ExcessiveAttemptsException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public ExcessiveAttemptsException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ExcessiveAttemptsException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public ExcessiveAttemptsException(String message, Throwable cause) {
        super(message, cause);
    }
}
