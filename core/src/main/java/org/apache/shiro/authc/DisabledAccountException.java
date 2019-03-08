package org.apache.shiro.authc;

/**
 * 账号不可用异常类
 */
public class DisabledAccountException extends AccountException {

    /**
     * Creates a new DisabledAccountException.
     */
    public DisabledAccountException() {
        super();
    }

    /**
     * Constructs a new DisabledAccountException.
     *
     * @param message the reason for the exception
     */
    public DisabledAccountException(String message) {
        super(message);
    }

    /**
     * Constructs a new DisabledAccountException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public DisabledAccountException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new DisabledAccountException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public DisabledAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
