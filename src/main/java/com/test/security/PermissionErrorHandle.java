package com.test.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PermissionErrorHandle implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        // 权限认证失败  重定向 到 权限错误提示页面
        System.out.println("该接口权限认证失败  重定向 到 权限错误提示页面");
//        response.sendRedirect("/index/error?message=Access Denied!&detail=You do not have permission to access this resource!");

        System.out.println(" 403 自定义 CustomAccessDeniedHandler 请求中包含有效的 JWT，但用户没有所需的 ADMIN 权限，权限检查失败");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);  // 403 Forbidden
        response.getWriter().write("You do not have permission to access this resource.");  // 自定义消息

    }
}