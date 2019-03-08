package org.apache.shiro.authc;

/**
 * 锁定账号异常类
 */
public class LockedAccountException extends DisabledAccountException {

    /**
     * Creates a new LockedAccountException.
     */
    public LockedAccountException() {
        super();
    }

    /**
     * Constructs a new LockedAccountException.
     *
     * @param message the reason for the exception
     */
    public LockedAccountException(String message) {
        super(message);
    }

    /**
     * Constructs a new LockedAccountException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public LockedAccountException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new LockedAccountException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public LockedAccountException(String message, Throwable cause) {
        super(message, cause);
    }

}
