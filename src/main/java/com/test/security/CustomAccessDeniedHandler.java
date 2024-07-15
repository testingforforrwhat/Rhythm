package com.test.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        System.out.println(" 403 自定义 CustomAccessDeniedHandler 请求中包含有效的 JWT，但用户没有所需的 ADMIN 权限，权限检查失败");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);  // 403 Forbidden
        response.getWriter().write("You do not have permission to access this resource.");  // 自定义消息
    }
}