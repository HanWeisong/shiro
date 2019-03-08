package org.apache.shiro.authc;

/**
 * 可记住我的认证token
 */
public interface RememberMeAuthenticationToken extends AuthenticationToken {

    /**
     * 身份认证时是否标记记住我
     * @return
     */
    boolean isRememberMe();

}
