package org.apache.shiro.authc;

import org.apache.shiro.subject.PrincipalCollection;

/**
 * 注销回调接口
 */
public interface LogoutAware {

    /**
     * Subject注销系统时触发此方法
     * @param principals
     */
    public void onLogout(PrincipalCollection principals);
}
