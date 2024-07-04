package com.test.security;

import com.test.bean.po.Role;
import com.test.mapper.RoleMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Component
public class PermissionAuthority implements FilterInvocationSecurityMetadataSource {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        // 获取请求的URL
        String requestUrl = ( (FilterInvocation) object ).getRequestUrl();
        System.out.println("当前请求的URL: " + requestUrl);

        // 根据URL获取可以访问角色列表
        List<Role> roleList = roleMapper.getListByOperateUrl(requestUrl);

        // 判断角色列表是否为空
        if( !roleList.isEmpty() && roleList.size() > 0 ){
            // 准备数组存放授权列表
            String[] roles = new String[roleList.size()];
            // 循环角色列表中的每一个元素
            for( int i = 0 ; i <= roles.length - 1; i++ ){
                // 将当前循环到角色存放到授权列表中
                roles[i] = roleList.get(i).getRoleName();
            }
            // 返回授权列表
            return SecurityConfig.createList(roles);
        }
        // 避免返回null（任何角色都能访问）
        return SecurityConfig.createList("PublicPermission");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}