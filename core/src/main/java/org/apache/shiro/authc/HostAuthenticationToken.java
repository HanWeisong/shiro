package org.apache.shiro.authc;

/**
 * 主机认证token
 */
public interface HostAuthenticationToken extends AuthenticationToken {

    /**
     * 用户登录时的Host
     * @return
     */
    String getHost();
}
