package org.apache.shiro.authc;

/**
 * 认证器接口
 */
public interface Authenticator {

    /**
     * 根据认证token进行认证，返回认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    public AuthenticationInfo authenticate(AuthenticationToken authenticationToken) throws AuthenticationException;

}
