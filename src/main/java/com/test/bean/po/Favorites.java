package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName favorites
 */
@TableName(value ="favorites")
@Data
public class Favorites implements Serializable {
    /**
     * 收藏ID
     */
    @TableId(type = IdType.AUTO)
    private Integer favoriteId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 音乐ID
     */
    private Integer musicId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;


}