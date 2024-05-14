package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName music
 */
@TableName(value ="music")
@Data
public class Music implements Serializable {
    /**
     * 音乐ID
     */
    @TableId(type = IdType.AUTO)
    private Integer musicId;

    /**
     * 歌曲名称
     */
    private String title;

    /**
     * 歌手
     */
    private String artist;

    /**
     * 专辑
     */
    private String album;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 音乐文件路径
     */
    private String musicFile;

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

    /**
     * 播放次数统计
     */
    private Integer musicPlayCount;

    /**
     * 本周播放次数统计
     */
    private Integer musicPlayCountWeek;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}