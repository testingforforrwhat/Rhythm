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
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

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
        Music other = (Music) that;
        return (this.getMusicId() == null ? other.getMusicId() == null : this.getMusicId().equals(other.getMusicId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getArtist() == null ? other.getArtist() == null : this.getArtist().equals(other.getArtist()))
            && (this.getAlbum() == null ? other.getAlbum() == null : this.getAlbum().equals(other.getAlbum()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getMusicFile() == null ? other.getMusicFile() == null : this.getMusicFile().equals(other.getMusicFile()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMusicId() == null) ? 0 : getMusicId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getArtist() == null) ? 0 : getArtist().hashCode());
        result = prime * result + ((getAlbum() == null) ? 0 : getAlbum().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getMusicFile() == null) ? 0 : getMusicFile().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", musicId=").append(musicId);
        sb.append(", title=").append(title);
        sb.append(", artist=").append(artist);
        sb.append(", album=").append(album);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", musicFile=").append(musicFile);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}