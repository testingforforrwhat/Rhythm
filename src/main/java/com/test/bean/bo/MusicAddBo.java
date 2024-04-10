package com.test.bean.bo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 音乐添加业务模型对象
 * @TableName music
 */
@TableName(value ="music")
@Data
public class MusicAddBo implements Serializable {
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


}