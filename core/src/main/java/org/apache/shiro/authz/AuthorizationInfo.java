package org.apache.shiro.authz;

import java.io.Serializable;
import java.util.Collection;

/**
 * 授权信息接口
 */
public interface AuthorizationInfo extends Serializable {

    /**
     * 获取角色集合
     * @return
     */
    Collection<String> getRoles();

    /**
     * 获取权限集合
     * @return
     */
    Collection<String> getStringPermissions();

    /**
     * 获取权限对象集合
     * @return
     */
    Collection<Permission> getObjectPermissions();

}
