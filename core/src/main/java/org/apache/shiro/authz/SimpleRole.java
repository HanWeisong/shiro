package org.apache.shiro.authz;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 简单角色
 */
public class SimpleRole implements Serializable {

    /**
     * 角色
     */
    protected String name = null;

    /**
     * 权限集合
     */
    protected Set<Permission> permissions;

    /**
     * 构造方法
     */
    public SimpleRole() {
    }

    /**
     * 构造方法
     * @param name
     */
    public SimpleRole(String name) {
        setName(name);
    }

    /**
     * 构造方法
     * @param name
     * @param permissions
     */
    public SimpleRole(String name, Set<Permission> permissions) {
        setName(name);
        setPermissions(permissions);
    }

    /**
     * 获取角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取权限集合
     * @return
     */
    public Set<Permission> getPermissions() {
        return permissions;
    }

    /**
     * 设置权限集合
     * @param permissions
     */
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * 添加权限
     * @param permission
     */
    public void add(Permission permission) {
        Set<Permission> permissions = getPermissions();
        if (permissions == null) {
            permissions = new LinkedHashSet<Permission>();
            setPermissions(permissions);
        }
        permissions.add(permission);
    }

    /**
     * 添加权限集合
     * @param perms
     */
    public void addAll(Collection<Permission> perms) {
        if (perms != null && !perms.isEmpty()) {
            Set<Permission> permissions = getPermissions();
            if (permissions == null) {
                permissions = new LinkedHashSet<Permission>(perms.size());
                setPermissions(permissions);
            }
            permissions.addAll(perms);
        }
    }

    /**
     * 是否支持此权限
     * @param p
     * @return
     */
    public boolean isPermitted(Permission p) {
        Collection<Permission> perms = getPermissions();
        if (perms != null && !perms.isEmpty()) {
            for (Permission perm : perms) {
                if (perm.implies(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return (getName() != null ? getName().hashCode() : 0);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SimpleRole) {
            SimpleRole sr = (SimpleRole) o;
            //only check name, since role names should be unique across an entire application:
            return (getName() != null ? getName().equals(sr.getName()) : sr.getName() == null);
        }
        return false;
    }

    public String toString() {
        return getName();
    }
}
