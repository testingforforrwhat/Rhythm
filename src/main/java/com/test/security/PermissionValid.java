package com.test.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PermissionValid implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        System.out.println( "-------PermissionValid implements AccessDecisionManager-------");

        // 迭代遍历当前请求的URL授权的所有角色
        for( ConfigAttribute attribute : configAttributes ){

            // 判断当前账户是否为空
            if (authentication == null) {
                throw new AccessDeniedException("权限认证失败！");
            }

            // 增加对路径的检测
            if (object instanceof FilterInvocation) {
                FilterInvocation fi = (FilterInvocation) object;
                String requestUrl = fi.getRequestUrl();
                if ("/springSecurity/login".equals(requestUrl)) {
                    // 直接放行 /springSecurity/login
                    return;
                }
            }

            // 获取当前请求的授权角色
            String needRole = attribute.getAttribute();
            System.out.println("当前url请求的授权角色: " + needRole);

            System.out.println( "-------开始认证-------");
            // 判断是否是默认权限
            if ("PublicPermission".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录");
                } else
                    return;
            }
            // 获取当前账户所拥有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // 迭代遍历当前账户的每一个角色
            for (GrantedAuthority authority : authorities) {
                // 判断当前账户的角色是否和当前循环到的授权角色相同
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        // 认证失败
        throw new AccessDeniedException("权限认证失败！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}