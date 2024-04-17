package com.test.bean.bo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName favorites
 */

@Data
public class FavoritesAddBo implements Serializable {


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