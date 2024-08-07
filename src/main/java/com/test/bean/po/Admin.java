package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @TableName admin
 */
@TableName(value ="admin")
@Data
public class Admin implements Serializable, UserDetails {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer adminId;

    /**
     * 
     */
    private String adminName;

    /**
     * 
     */
    private String adminPass;

    /**
     * 
     */
    private String adminNickname;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // Association 组合关系
    // Collection 聚合关系
    private List<Role> roleList;  // 登录用户的角色集合
    private List<Menu> menuList;  // 登录用户的菜单集合

    // UserDetails 用户类接口中的抽象方法
    /**
     * 获取当前用户的授权集合 => 获取当前用户拥有的角色列表
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleList();
    }

    @Override
    public String getPassword() {
        return getAdminPass();
    }

    @Override
    public String getUsername() {
        return getAdminName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}