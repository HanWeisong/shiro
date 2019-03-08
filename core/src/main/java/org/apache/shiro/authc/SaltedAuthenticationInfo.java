package org.apache.shiro.authc;

import org.apache.shiro.util.ByteSource;

/**
 * 加盐的认证token
 */
public interface SaltedAuthenticationInfo extends AuthenticationInfo {

    /**
     * 返回用于加密帐户凭据的salt
     * @return
     */
    ByteSource getCredentialsSalt();
}
