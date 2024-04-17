package com.test.bean.bo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 音乐分类编辑业务模型对象
 * @TableName music_categories
 */

@Data
public class MusicCategoriesUpdateBo implements Serializable {
    /**
     * 分类ID
     */
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}