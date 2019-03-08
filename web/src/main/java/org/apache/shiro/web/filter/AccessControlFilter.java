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
package org.apache.shiro.web.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

// 过滤请求，判断是否认证和授权，如未认证则重定向到登录也，如未授权则重定向未授权页或返回401
public abstract class AccessControlFilter extends PathMatchingFilter {

    public static final String DEFAULT_LOGIN_URL = "/login.jsp";

    public static final String GET_METHOD = "GET";

    public static final String POST_METHOD = "POST";

    // 默认登录地址
    private String loginUrl = DEFAULT_LOGIN_URL;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    // 从安全工具类（ThreadLocal）获取当前subject信息
    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return SecurityUtils.getSubject();
    }

    // 子类实现判断是否认证和授权逻辑
    protected abstract boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception;

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return onAccessDenied(request, response);
    }

    // 处理被isAccessAllowed方法拒绝的请求，处理过程中发生错误抛出异常
    protected abstract boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception;

    // 1.判断是否已认证和已授权 2.处理未认证或未授权
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return isAccessAllowed(request, response, mappedValue) || onAccessDenied(request, response, mappedValue);
    }

    // 判断是否是登录请求
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return pathsMatch(getLoginUrl(), request);
    }

    // 1.保存当前请求到session 2.重定向到登录地址
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        saveRequest(request);
        redirectToLogin(request, response);
    }

    // 保存当前请求（请求方法、地址、请求参数）到 session 中
    protected void saveRequest(ServletRequest request) {
        WebUtils.saveRequest(request);
    }

    // 重定向到登录地址
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        String loginUrl = getLoginUrl();
        WebUtils.issueRedirect(request, response, loginUrl);
    }

}
