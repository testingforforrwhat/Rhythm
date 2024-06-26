package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName permission_menu
 */
@TableName(value ="permission_menu")
@Data
public class PermissionMenu implements Serializable {
    /**
     * 
     */
    private Integer permissionId;

    /**
     * 
     */
    private Integer menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}