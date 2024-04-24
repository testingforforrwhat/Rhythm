package com.test.bean.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 音乐查询业务模型对象
 * @TableName music
 */

@Data
public class MusicSearchBo extends BaseSearchBo implements Serializable {


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