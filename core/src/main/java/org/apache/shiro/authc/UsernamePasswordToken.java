package org.apache.shiro.authc;

/**
 * 用户名密码token
 */
public class UsernamePasswordToken implements HostAuthenticationToken, RememberMeAuthenticationToken {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private char[] password;

    /**
     * 是否记住我
     */
    private boolean rememberMe = false;

    /**
     * 用户登录时的Host
     */
    private String host;

    public UsernamePasswordToken() {

    }

    public UsernamePasswordToken(final String username, final char[] password) {
        this(username, password, false, null);
    }

    public UsernamePasswordToken(final String username, final String password) {
        this(username, password != null ? password.toCharArray() : null, false, null);
    }

    public UsernamePasswordToken(final String username, final char[] password, final String host) {
        this(username, password, false, host);
    }

    public UsernamePasswordToken(final String username, final String password, final String host) {
        this(username, password != null ? password.toCharArray() : null, false, host);
    }

    public UsernamePasswordToken(final String username, final char[] password, final boolean rememberMe) {
        this(username, password, rememberMe, null);
    }

    public UsernamePasswordToken(final String username, final String password, final boolean rememberMe) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, null);
    }

    public UsernamePasswordToken(final String username, final char[] password, final boolean rememberMe, final String host) {
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public UsernamePasswordToken(final String username, final String password, final boolean rememberMe, final String host) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public Object getPrincipal() {
        return getUsername();
    }

    public Object getCredentials() {
        return getPassword();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void clear() {
        this.username = null;
        this.host = null;
        this.rememberMe = false;

        if (this.password != null) {
            for (int i = 0; i < password.length; i++) {
                this.password[i] = 0x00;
            }
            this.password = null;
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(username);
        sb.append(", rememberMe=").append(rememberMe);
        if (host != null) {
            sb.append(" (").append(host).append(")");
        }
        return sb.toString();
    }

}
