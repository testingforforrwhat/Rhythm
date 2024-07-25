package com.test.security;

import com.test.service.AdminService;
import com.test.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * 实现一个过滤器，以在每个请求中验证 JWT 令牌
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private AdminService adminService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        System.out.println( "-------JwtRequestFilter, 开始jwt校验-------");

        // 打印调试日志
        System.out.println("Processing request for path: " + request.getServletPath());

        // 忽略 /springSecurity/login 路径
        if ("/api/springSecurity/login".equals(request.getServletPath())) {
            System.out.println("Ignoring filter for path: " + request.getServletPath());
            chain.doFilter(request, response);
            return;
        }

        // 1. 从请求头中提取 JWT 令牌
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                System.out.println("jwtTokenUtil.getUsernameFromToken: " + username);
            } catch (Exception e) {
                System.out.println("JWT token is invalid or expired");
                logger.warn("JWT token is invalid or expired");
            }
        } else {
            logger.warn("JWT token does not begin with Bearer String");
        }

        // 2. 如果 SecurityContext 还没有设置认证信息
        /**
         *
         * 后端通过一个过滤器来拦截所有请求，验证 JWT 并解析其中的信息，然后将解析得到的 Authentication 对象保存到安全上下文中
         *
         * SecurityContextHolder.getContext().setAuthentication(authentication); 确实是在登录时手动设置了认证对象。这个操作通常会在用户登录成功时进行，用于确保用户的认证信息正确保存到当前的安全上下文中。
         * 不过，这样的设置主要是对一次请求有效。
         *
         * 在用户成功登录后，通过 SecurityContextHolder.getContext().setAuthentication(authentication) 手动设置认证对象，这在仅当前请求中生效。
         *
         *
         * 每次请求和每个请求自动认证：通过 JWT(JwtRequestFilter.java) 实现无状态认证，每次请求携带 Token，由后端过滤器验证 Token 并(JwtRequestFilter.java)设置认证对象，从而保证每个请求的认证状态。
         *
         *
         */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtTokenUtil.validateToken(jwtToken, username)) {

                System.out.println("SecurityContextHolder.getContext().getAuthentication(authentication): " + SecurityContextHolder.getContext().getAuthentication());

                System.out.println( "-------JwtRequestFilter, 通过jwt校验,通过登录认证, 开始授权校验  .getAuthorities()  -------");

                // 3. 创建包含权限的 UsernamePasswordAuthenticationToken 对象
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        username, null, adminService.loadUserByUsername(username).getAuthorities());
                // 使用 WebAuthenticationDetailsSource 设置请求的详细信息（如 IP 地址、会话 ID 等）
                // WebAuthenticationDetailsSource 是一个工具类，用于从 HttpServletRequest 中提取详细信息，
                // 并将其添加到 WebAuthenticationDetails 对象中。这些详细信息通常包括客户端的 IP 地址和会话 ID。
                //
                // 我们构建 UsernamePasswordAuthenticationToken 对象来存储认证(authentication)信息
                // 我们可以使用 WebAuthenticationDetailsSource 为构建的 UsernamePasswordAuthenticationToken 添加额外的请求的上下文信息。这不仅有助于审计和监控，还能帮助我们实现一些特定的安全需求，例如防止多次登录攻击。

                // 4. 将详细信息设置到 UsernamePasswordAuthenticationToken 认证对象中，并更新 SecurityContext
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


                Authentication authentication_ = SecurityContextHolder.getContext().getAuthentication();
                System.out.println("authentication_  (SecurityContextHolder.getContext().getAuthentication(authentication)): \n" + SecurityContextHolder.getContext().getAuthentication());
                System.out.println("authentication_.getDetails(): \n" + authentication_.getDetails());
                if (authentication_ != null && authentication_.getDetails() instanceof WebAuthenticationDetails) {
                    WebAuthenticationDetails details = (WebAuthenticationDetails) authentication_.getDetails();

                    // 获取 IP 地址
                    String remoteAddress = details.getRemoteAddress();

                    // 获取会话 ID
                    String sessionId = details.getSessionId();

                    // 打印 IP 地址和会话 ID
                    System.out.println("Remote IP Address: " + remoteAddress);
                    System.out.println("Session ID: " + sessionId);
                }


            }
        }

        chain.doFilter(request, response);
    }
}