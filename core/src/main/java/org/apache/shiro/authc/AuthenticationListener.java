package org.apache.shiro.authc;

import org.apache.shiro.subject.PrincipalCollection;

/**
 * 认证监听器接口
 */
public interface AuthenticationListener {

    /**
     * 监听认证成功接口
     * @param token
     * @param info
     */
    void onSuccess(AuthenticationToken token, AuthenticationInfo info);

    /**
     * 监听认证失败接口
     * @param token
     * @param ae
     */
    void onFailure(AuthenticationToken token, AuthenticationException ae);

    /**
     * 监听logout接口
     * @param principals
     */
    void onLogout(PrincipalCollection principals);

}
