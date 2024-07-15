package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @TableName role
 */
@TableName(value ="role")
@Data
public class Role implements Serializable, GrantedAuthority {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    /**
     * 
     */
    private String roleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @Override
    public String getAuthority() {

        // 手动添加 `ROLE_` 前缀以匹配 Spring Security 的期望
        return "ROLE_" + getRoleName();
    }
}