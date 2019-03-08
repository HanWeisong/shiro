package org.apache.shiro.authc;

/**
 * 并发访问异常
 */
public class ConcurrentAccessException extends AccountException {

    /**
     * Creates a new ConcurrentAccessException.
     */
    public ConcurrentAccessException() {
        super();
    }

    /**
     * Constructs a new ConcurrentAccessException.
     *
     * @param message the reason for the exception
     */
    public ConcurrentAccessException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConcurrentAccessException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public ConcurrentAccessException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ConcurrentAccessException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public ConcurrentAccessException(String message, Throwable cause) {
        super(message, cause);
    }

}
