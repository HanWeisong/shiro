package org.apache.shiro.authz;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 简单授权信息
 */
public class SimpleAuthorizationInfo implements AuthorizationInfo {

    /**
     * The internal roles collection.
     */
    /**
     * 角色集合
     */
    protected Set<String> roles;

    /**
     * Collection of all string-based permissions associated with the account.
     */
    /**
     * 权限集合
     */
    protected Set<String> stringPermissions;

    /**
     * Collection of all object-based permissions associaed with the account.
     */
    /**
     * 权限对象集合
     */
    protected Set<Permission> objectPermissions;

    /**
     * Default no-argument constructor.
     */
    public SimpleAuthorizationInfo() {
    }

    /**
     * Creates a new instance with the specified roles and no permissions.
     * @param roles the roles assigned to the realm account.
     */

    /**
     * 构造方法
     * @param roles
     */
    public SimpleAuthorizationInfo(Set<String> roles) {
        this.roles = roles;
    }

    /**
     * 获取权限集合
     * @return
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * Sets the roles assigned to the account.
     * @param roles the roles assigned to the account.
     */
    /**
     * 设置权限集合
     * @param roles
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /**
     * 添加角色
     * @param role
     */
    public void addRole(String role) {
        if (this.roles == null) {
            this.roles = new HashSet<String>();
        }
        this.roles.add(role);
    }

    /**
     * 添加多个角色
     * @param roles
     */
    public void addRoles(Collection<String> roles) {
        if (this.roles == null) {
            this.roles = new HashSet<String>();
        }
        this.roles.addAll(roles);
    }

    /**
     * 获取权限集合
     * @return
     */
    public Set<String> getStringPermissions() {
        return stringPermissions;
    }

    /**
     * 设置权限结婚
     * @param stringPermissions
     */
    public void setStringPermissions(Set<String> stringPermissions) {
        this.stringPermissions = stringPermissions;
    }

    /**
     * 添加权限
     * @param permission
     */
    public void addStringPermission(String permission) {
        if (this.stringPermissions == null) {
            this.stringPermissions = new HashSet<String>();
        }
        this.stringPermissions.add(permission);
    }

    /**
     * 添加多个权限
     * @param permissions
     */
    public void addStringPermissions(Collection<String> permissions) {
        if (this.stringPermissions == null) {
            this.stringPermissions = new HashSet<String>();
        }
        this.stringPermissions.addAll(permissions);
    }

    /**
     * 获取权限集合
     * @return
     */
    public Set<Permission> getObjectPermissions() {
        return objectPermissions;
    }

    /**
     * 设置权限集合
     * @param objectPermissions
     */
    public void setObjectPermissions(Set<Permission> objectPermissions) {
        this.objectPermissions = objectPermissions;
    }

    /**
     * 添加权限对象
     * @param permission
     */
    public void addObjectPermission(Permission permission) {
        if (this.objectPermissions == null) {```
            this.objectPermissions = new HashSet<Permission>();
        }
        this.objectPermissions.add(permission);
    }

    /**
     * 添加多个权限对象
     * @param permissions
     */
    public void addObjectPermissions(Collection<Permission> permissions) {
        if (this.objectPermissions == null) {
            this.objectPermissions = new HashSet<Permission>();
        }
        this.objectPermissions.addAll(permissions);
    }
}
