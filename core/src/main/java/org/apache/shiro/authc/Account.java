package org.apache.shiro.authc;

import org.apache.shiro.authz.AuthorizationInfo;

/**
 * 账号信息接口（认证接口和授权接口的集合）
 */
public interface Account extends AuthenticationInfo, AuthorizationInfo {

}
