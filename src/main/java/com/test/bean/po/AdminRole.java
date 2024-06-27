package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName admin_role
 */
@TableName(value ="admin_role")
@Data
public class AdminRole implements Serializable {
    /**
     * 
     */
    private Integer adminId;

    /**
     * 
     */
    private Integer roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}