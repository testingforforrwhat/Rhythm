package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName operate
 */
@TableName(value ="operate")
@Data
public class Operate implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer operateId;

    /**
     * 
     */
    private String operateName;

    /**
     * 
     */
    private String operateUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}