package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 菜单实体模型类
 * @TableName menu
 */
@TableName(value ="menu")
@Data
public class Menu implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer menuId;

    /**
     * 
     */
    private String menuName;

    /**
     * 
     */
    private String menuUrl;

    /**
     * 
     */
    private Integer pid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}