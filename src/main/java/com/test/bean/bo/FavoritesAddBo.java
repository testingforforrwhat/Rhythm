package com.test.bean.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName favorites
 */
@TableName(value ="favorites")
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


}