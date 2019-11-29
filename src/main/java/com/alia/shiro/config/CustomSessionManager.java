package com.alia.shiro.config;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义SessionManager
 *
 * @author Simon
 */
public class CustomSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "token";

    public CustomSessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        if (null == sessionId) {
            return super.getSessionId(request, response);
        }

        // 从父类 DefaultWebSessionManager 中的方法 getReferencedSessionId 拷贝一下源码
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);

        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
        // 在此处自动将其标记为有效; 如果无效, 则将调用下面的onUnknownSession方法, 届时我们将删除该属性
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

        return sessionId;
    }
}
