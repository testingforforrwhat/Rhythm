package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName play_history
 */
@TableName(value ="play_history")
@Data
public class PlayHistory implements Serializable {
    /**
     * 播放历史ID
     */
    @TableId(type = IdType.AUTO)
    private Integer historyId;

    /**
     * 用户ID，外键关联用户表
     */
    private Integer userId;

    /**
     * 歌曲ID，外键关联歌曲表
     */
    private Integer songId;

    /**
     * 播放日期
     */
    private Date playDate;

    /**
     * 播放平台
     */
    private String platform;

    /**
     * 播放时长（秒)
     */
    private Integer durationSeconds;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PlayHistory other = (PlayHistory) that;
        return (this.getHistoryId() == null ? other.getHistoryId() == null : this.getHistoryId().equals(other.getHistoryId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSongId() == null ? other.getSongId() == null : this.getSongId().equals(other.getSongId()))
            && (this.getPlayDate() == null ? other.getPlayDate() == null : this.getPlayDate().equals(other.getPlayDate()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getDurationSeconds() == null ? other.getDurationSeconds() == null : this.getDurationSeconds().equals(other.getDurationSeconds()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHistoryId() == null) ? 0 : getHistoryId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSongId() == null) ? 0 : getSongId().hashCode());
        result = prime * result + ((getPlayDate() == null) ? 0 : getPlayDate().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getDurationSeconds() == null) ? 0 : getDurationSeconds().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", historyId=").append(historyId);
        sb.append(", userId=").append(userId);
        sb.append(", songId=").append(songId);
        sb.append(", playDate=").append(playDate);
        sb.append(", platform=").append(platform);
        sb.append(", durationSeconds=").append(durationSeconds);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}