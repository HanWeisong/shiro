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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// logoutfilter
public class LogoutFilter extends AdviceFilter {
    
    private static final Logger log = LoggerFactory.getLogger(LogoutFilter.class);

    // 默认lougou重定向地址
    public static final String DEFAULT_REDIRECT_URL = "/";

    private String redirectUrl = DEFAULT_REDIRECT_URL;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        // 获取当前请求的subject
        Subject subject = getSubject(request, response);
        // 获取登出的重定向地址
        String redirectUrl = getRedirectUrl(request, response, subject);
        //try/catch added for SHIRO-298:
        try {
            // logout
            // 1.删除 .RUN_AS_PRINCIPALS_SESSION_KEY 信息
            // 2.rememberMe deleteMe标志
            // 3.logout通知
            // 4.遍历多个real 清空session
            // 5.从 session 中删除 _AUTHENTICATED_SESSION_KEY _PRINCIPALS_SESSION_KEY 信息
            subject.logout();
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        // 重定向到登出地址
        issueRedirect(request, response, redirectUrl);
        // 返回标示，不继续filter的执行
        return false;
    }

    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return SecurityUtils.getSubject();
    }

    protected void issueRedirect(ServletRequest request, ServletResponse response, String redirectUrl) throws Exception {
        WebUtils.issueRedirect(request, response, redirectUrl);
    }

    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        return getRedirectUrl();
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
