package com.test.service;

import com.test.bean.bo.MusicAddBo;
import com.test.bean.bo.MusicUpdateBo;
import com.test.bean.po.Music;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23194
* @description 针对表【music】的数据库操作Service
* @createDate 2024-04-03 12:32:12
*/
public interface MusicService extends IService<Music> {

    /**
     * 根据music_id查询对应信息
     * @param musicId
     * @return
     */
    Object selectByMusicId(Integer musicId);

    /**
     * 查询当前所有音乐
     * @return
     */
    Object listAll();

    /**
     * 添加音乐
     * @param musicAddBo
     * @return
     */
    boolean add(MusicAddBo musicAddBo);

    /**
     * 编辑音乐
     * @param musicUpdateBo
     * @return
     */
    boolean updateMusic(MusicUpdateBo musicUpdateBo);

    /**
     * 删除音乐
     * @param musicId
     * @return
     */
    boolean deleteMusic(Integer musicId);
}
