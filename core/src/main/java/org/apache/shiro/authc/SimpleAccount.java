/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.shiro.authc;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;


/**
 * Simple implementation of the {@link org.apache.shiro.authc.Account} interface that
 * contains principal and credential and authorization information (roles and permissions) as instance variables and
 * exposes them via getters and setters using standard JavaBean notation.
 *
 * @since 0.1
 */

/**
 * 简单账号类
 */
public class SimpleAccount implements Account, MergableAuthenticationInfo, SaltedAuthenticationInfo, Serializable {

    /**
     * 账号的认证信息
     */
    private SimpleAuthenticationInfo authcInfo;

    /**
     * 账号的授权信息
     */
    private SimpleAuthorizationInfo authzInfo;

    /**
     * 账号是否被锁定
     */
    private boolean locked;

    /**
     * 秘钥是否过期
     */
    private boolean credentialsExpired;

    public SimpleAccount() {

    }

    public SimpleAccount(Object principal, Object credentials, String realmName) {
        this(principal instanceof PrincipalCollection ? (PrincipalCollection) principal : new SimplePrincipalCollection(principal, realmName), credentials);
    }

    public SimpleAccount(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName) {
        this(principal instanceof PrincipalCollection ? (PrincipalCollection) principal : new SimplePrincipalCollection(principal, realmName),
                hashedCredentials, credentialsSalt);
    }

    public SimpleAccount(Collection principals, Object credentials, String realmName) {
        this(new SimplePrincipalCollection(principals, realmName), credentials);
    }

    public SimpleAccount(PrincipalCollection principals, Object credentials) {
        this.authcInfo = new SimpleAuthenticationInfo(principals, credentials);
        this.authzInfo = new SimpleAuthorizationInfo();
    }

    public SimpleAccount(PrincipalCollection principals, Object hashedCredentials, ByteSource credentialsSalt) {
        this.authcInfo = new SimpleAuthenticationInfo(principals, hashedCredentials, credentialsSalt);
        this.authzInfo = new SimpleAuthorizationInfo();
    }

    public SimpleAccount(PrincipalCollection principals, Object credentials, Set<String> roles) {
        this.authcInfo = new SimpleAuthenticationInfo(principals, credentials);
        this.authzInfo = new SimpleAuthorizationInfo(roles);
    }

    public SimpleAccount(Object principal, Object credentials, String realmName, Set<String> roleNames, Set<Permission> permissions) {
        this.authcInfo = new SimpleAuthenticationInfo(new SimplePrincipalCollection(principal, realmName), credentials);
        this.authzInfo = new SimpleAuthorizationInfo(roleNames);
        this.authzInfo.setObjectPermissions(permissions);
    }

    public SimpleAccount(Collection principals, Object credentials, String realmName, Set<String> roleNames, Set<Permission> permissions) {
        this.authcInfo = new SimpleAuthenticationInfo(new SimplePrincipalCollection(principals, realmName), credentials);
        this.authzInfo = new SimpleAuthorizationInfo(roleNames);
        this.authzInfo.setObjectPermissions(permissions);
    }

    public SimpleAccount(PrincipalCollection principals, Object credentials, Set<String> roleNames, Set<Permission> permissions) {
        this.authcInfo = new SimpleAuthenticationInfo(principals, credentials);
        this.authzInfo = new SimpleAuthorizationInfo(roleNames);
        this.authzInfo.setObjectPermissions(permissions);
    }

    public PrincipalCollection getPrincipals() {
        return authcInfo.getPrincipals();
    }

    public void setPrincipals(PrincipalCollection principals) {
        this.authcInfo.setPrincipals(principals);
    }

    public Object getCredentials() {
        return authcInfo.getCredentials();
    }

    public void setCredentials(Object credentials) {
        this.authcInfo.setCredentials(credentials);
    }

    public ByteSource getCredentialsSalt() {
        return this.authcInfo.getCredentialsSalt();
    }

    public void setCredentialsSalt(ByteSource salt) {
        this.authcInfo.setCredentialsSalt(salt);
    }

    public Collection<String> getRoles() {
        return authzInfo.getRoles();
    }

    public void setRoles(Set<String> roles) {
        this.authzInfo.setRoles(roles);
    }

    public void addRole(String role) {
        this.authzInfo.addRole(role);
    }

    public void addRole(Collection<String> roles) {
        this.authzInfo.addRoles(roles);
    }

    public Collection<String> getStringPermissions() {
        return authzInfo.getStringPermissions();
    }

    public void setStringPermissions(Set<String> permissions) {
        this.authzInfo.setStringPermissions(permissions);
    }

    public void addStringPermission(String permission) {
        this.authzInfo.addStringPermission(permission);
    }

    public void addStringPermissions(Collection<String> permissions) {
        this.authzInfo.addStringPermissions(permissions);
    }

    public Collection<Permission> getObjectPermissions() {
        return authzInfo.getObjectPermissions();
    }

    public void setObjectPermissions(Set<Permission> permissions) {
        this.authzInfo.setObjectPermissions(permissions);
    }

    public void addObjectPermission(Permission permission) {
        this.authzInfo.addObjectPermission(permission);
    }

    public void addObjectPermissions(Collection<Permission> permissions) {
        this.authzInfo.addObjectPermissions(permissions);
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     * 合并账户信息（认证信息、授权信息、账号锁定信息、账号密码是否过期信息）
     * @param info
     */
    public void merge(AuthenticationInfo info) {
        authcInfo.merge(info);

        // Merge SimpleAccount specific info
        if (info instanceof SimpleAccount) {
            SimpleAccount otherAccount = (SimpleAccount) info;
            if (otherAccount.isLocked()) {
                setLocked(true);
            }

            if (otherAccount.isCredentialsExpired()) {
                setCredentialsExpired(true);
            }
        }
    }

    public int hashCode() {
        return (getPrincipals() != null ? getPrincipals().hashCode() : 0);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SimpleAccount) {
            SimpleAccount sa = (SimpleAccount) o;
            //principal should be unique across the application, so only check this for equality:
            return (getPrincipals() != null ? getPrincipals().equals(sa.getPrincipals()) : sa.getPrincipals() == null);
        }
        return false;
    }

    public String toString() {
        return getPrincipals() != null ? getPrincipals().toString() : "empty";
    }

}