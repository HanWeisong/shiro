package org.apache.shiro.authc;

import java.io.Serializable;

/**
 * 认证token接口
 */
public interface AuthenticationToken extends Serializable {

    /**
     * 获得账号名（账号、手机、邮箱等用户唯一标示信息）
     * @return
     */
    Object getPrincipal();

    /**
     * 获得账号名对应的秘钥或密码
     * @return
     */
    Object getCredentials();

}
