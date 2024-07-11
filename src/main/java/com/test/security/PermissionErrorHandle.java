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
        System.out.println("权限认证失败  重定向 到 权限错误提示页面");
        response.sendRedirect("/index/error?message=Access Denied!&detail=You do not have permission to access this resource!");
    }
}