package org.apache.shiro.authc;

import org.apache.shiro.subject.PrincipalCollection;

import java.io.Serializable;

/**
 * 认证信息接口
 */
public interface AuthenticationInfo extends Serializable {

    /**
     * 返回与相应Subject相关联的所有主体。
     * @return
     */
    PrincipalCollection getPrincipals();


    /**
     * 获取密码信息
     * @return
     */
    Object getCredentials();

}
