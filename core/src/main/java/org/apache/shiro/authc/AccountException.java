package org.apache.shiro.authc;

/**
 * 账号异常
 */
public class AccountException extends AuthenticationException {

    /**
     * Creates a new AccountException.
     */
    public AccountException() {
        super();
    }

    /**
     * Constructs a new AccountException.
     *
     * @param message the reason for the exception
     */
    public AccountException(String message) {
        super(message);
    }

    /**
     * Constructs a new AccountException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public AccountException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new AccountException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }

}
