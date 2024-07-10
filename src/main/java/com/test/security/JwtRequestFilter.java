package com.test.security;

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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
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

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtTokenUtil.validateToken(jwtToken, username)) {

                System.out.println("SecurityContextHolder.getContext().getAuthentication(authentication): " + SecurityContextHolder.getContext().getAuthentication());

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>());
                // 使用 WebAuthenticationDetailsSource 设置请求的详细信息（如 IP 地址、会话 ID 等）
                // WebAuthenticationDetailsSource 是一个工具类，用于从 HttpServletRequest 中提取详细信息，
                // 并将其添加到 WebAuthenticationDetails 对象中。这些详细信息通常包括客户端的 IP 地址和会话 ID。
                //
                // 我们构建 UsernamePasswordAuthenticationToken 对象来存储认证(authentication)信息
                // 我们可以使用 WebAuthenticationDetailsSource 为构建的 UsernamePasswordAuthenticationToken 添加额外的请求的上下文信息。这不仅有助于审计和监控，还能帮助我们实现一些特定的安全需求，例如防止多次登录攻击。
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