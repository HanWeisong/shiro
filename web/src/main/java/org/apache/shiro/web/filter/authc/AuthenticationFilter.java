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
package org.apache.shiro.web.filter.authc;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// 认证过滤器
// 要求当前用户进行身份验证的所有过滤器的基类。, 此类封装*逻辑，用于检查用户是否已在系统中进行身份验证，而子类则需要为未经身份验证的请求执行*特定逻辑
public abstract class AuthenticationFilter extends AccessControlFilter {

    public static final String DEFAULT_SUCCESS_URL = "/";

    private String successUrl = DEFAULT_SUCCESS_URL;

    // 获取登录成功后跳转的地址
    public String getSuccessUrl() {
        return successUrl;
    }

    // 重写设置登录成功后跳转的地址
    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    // 是否允许访问 如果已认证则通过，如果未认证则不通过
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated();
    }

    // 发出成功重定向，登录成功后重定向到之前的访问的页面
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
    }

}
