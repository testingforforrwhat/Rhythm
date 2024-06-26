package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName permission_operate
 */
@TableName(value ="permission_operate")
@Data
public class PermissionOperate implements Serializable {
    /**
     * 
     */
    private Integer permissionId;

    /**
     * 
     */
    private Integer operateId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}