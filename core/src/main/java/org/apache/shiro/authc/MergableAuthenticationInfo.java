package org.apache.shiro.authc;

/**
 * 支持合并的账号认证信息接口
 */
public interface MergableAuthenticationInfo extends AuthenticationInfo {

    /**
     * 合并认证信息
     * @param info
     */
    void merge(AuthenticationInfo info);

}
