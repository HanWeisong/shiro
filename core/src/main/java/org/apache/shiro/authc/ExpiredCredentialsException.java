package org.apache.shiro.authc;

/**
 * 凭证过期异常类
 */
public class ExpiredCredentialsException extends CredentialsException {

    /**
     * Creates a new ExpiredCredentialsException.
     */
    public ExpiredCredentialsException() {
        super();
    }

    /**
     * Constructs a new ExpiredCredentialsException.
     *
     * @param message the reason for the exception
     */
    public ExpiredCredentialsException(String message) {
        super(message);
    }

    /**
     * Constructs a new ExpiredCredentialsException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public ExpiredCredentialsException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ExpiredCredentialsException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public ExpiredCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
