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

import javax.servlet.ServletContext;

// ServletContext 的基本封装
public class ServletContextSupport {

    //TODO - complete JavaDoc
    // ServletContext对象
    private ServletContext servletContext = null;

    // 获取 ServletContext
    public ServletContext getServletContext() {
        return servletContext;
    }

    // 设置 ServletContext
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    // 获取 ServletContext 的参数
    protected String getContextInitParam(String paramName) {
        return getServletContext().getInitParameter(paramName);
    }

    // 获取必须的 ServletContext 的对象，如为 null 则抛出异常
    private ServletContext getRequiredServletContext() {
        ServletContext servletContext = getServletContext();
        if (servletContext == null) {
            String msg = "ServletContext property must be set via the setServletContext method.";
            throw new IllegalStateException(msg);
        }
        return servletContext;
    }

    @SuppressWarnings({"UnusedDeclaration"})
    // 设置Servlet上下文参数，为null则清楚 key 不为空则保持 key 和 value 到上下文配置
    protected void setContextAttribute(String key, Object value) {
        if (value == null) {
            removeContextAttribute(key);
        } else {
            getRequiredServletContext().setAttribute(key, value);
        }
    }

    @SuppressWarnings({"UnusedDeclaration"})
    protected Object getContextAttribute(String key) {
        return getRequiredServletContext().getAttribute(key);
    }

    protected void removeContextAttribute(String key) {
        getRequiredServletContext().removeAttribute(key);
    }

    // 强烈建议不要直接覆盖此方法，而是覆盖* {@link #toStringBuilder（）toStringBuilder（）}方法，这是一种性能更佳的替代方法。
    @Override
    public String toString() {
        return toStringBuilder().toString();
    }

    // 与{@link #toString（）toString（）}相同的概念，但返回{@link StringBuilder}实例。 , * * @return一个StringBuilder实例，用于追加最终将从* {@code toString（）}调用返回的String数据
    protected StringBuilder toStringBuilder() {
        return new StringBuilder(super.toString());
    }
}
