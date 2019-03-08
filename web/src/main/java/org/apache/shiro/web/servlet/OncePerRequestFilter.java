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
package org.apache.shiro.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

// 过滤基类，保证每个请求只执行一次，*在任何servlet容器上执行。
public abstract class OncePerRequestFilter extends NameableFilter {

    private static final Logger log = LoggerFactory.getLogger(OncePerRequestFilter.class);

    // 附加到“已过滤”请求属性的过滤器名称的后缀
    public static final String ALREADY_FILTERED_SUFFIX = ".FILTERED";

    // 确定此过滤器是应该执行还是让请求进入下一个链元素
    private boolean enabled = true; //most filters wish to execute when configured, so default to true

    // 确定此过滤器是应该执行还是让请求进入下一个链元素
    public boolean isEnabled() {
        return enabled;
    }

    // 设置此过滤器要不要被执行
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // 他的{@code doFilter}实现为*“已经过滤”存储了一个请求属性，如果*属性已经存在，则不再过滤。
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String alreadyFilteredAttributeName = getAlreadyFilteredAttributeName();
        if ( request.getAttribute(alreadyFilteredAttributeName) != null ) {
            log.trace("Filter '{}' already executed.  Proceeding without invoking this filter.", getName());
            filterChain.doFilter(request, response);
        } else //noinspection deprecation
            if (/* added in 1.2: */ !isEnabled(request, response) ||
                /* retain backwards compatibility: */ shouldNotFilter(request) ) {
            log.debug("Filter '{}' is not enabled for the current request.  Proceeding without invoking this filter.",
                    getName());
            filterChain.doFilter(request, response);
        } else {
            // 设置已执行标示
            // Do invoke this filter...
            log.trace("Filter '{}' not yet executed.  Executing now.", getName());
            request.setAttribute(alreadyFilteredAttributeName, Boolean.TRUE);

            try {
                // 执行内部过滤逻辑
                doFilterInternal(request, response, filterChain);
            } finally {
                // Once the request has finished, we're done and we don't
                // need to mark as 'already filtered' any more.
                // 清除已执行标示
                request.removeAttribute(alreadyFilteredAttributeName);
            }
        }
    }

    // true 跳过此过滤器 false 执行此过滤器逻辑，可以子类覆盖
    protected boolean isEnabled(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        return isEnabled();
    }

    // 获取是否已执行参数名 过滤器名 + ".FILTERED"
    protected String getAlreadyFilteredAttributeName() {
        String name = getName();
        if (name == null) {
            name = getClass().getName();
        }
        return name + ALREADY_FILTERED_SUFFIX;
    }

    @Deprecated
    @SuppressWarnings({"UnusedDeclaration"})
    // 可子类重写内部逻辑 默认返回 false 是否不应该过滤此请求
    protected boolean shouldNotFilter(ServletRequest request) throws ServletException {
        return false;
    }

    // 过滤器内部实现，子类实现
    protected abstract void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException;
}
