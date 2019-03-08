package org.apache.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

public interface Realm {

    /**
     * 返回一个分配的系统唯一的Realm名称, 单个应用程序配置的所有领域必须具有唯一的名称
     * @return
     */
    String getName();

    /**
     * 此realm是否支持此类token认证
     * @param token
     * @return
     */
    boolean supports(AuthenticationToken token);

    /**
     * 返回token管理的认证信息，如没有则返回null
     * @param token
     * @return
     * @throws AuthenticationException
     */
    AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException;

}
