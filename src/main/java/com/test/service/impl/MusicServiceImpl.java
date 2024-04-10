package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.bo.MusicAddBo;
import com.test.bean.bo.MusicUpdateBo;
import com.test.bean.po.Music;
import com.test.service.MusicService;
import com.test.mapper.MusicMapper;
import org.springframework.stereotype.Service;

/**
* @author 23194
* @description 针对表【music】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
    implements MusicService{

    /**
     * 根据music_id查询对应信息
     *
     * @param musicId
     * @return
     */
    @Override
    public Object selectByMusicId(Integer musicId) {
        return null;
    }

    /**
     * 查询当前所有音乐
     *
     * @return
     */
    @Override
    public Object listAll() {
        return null;
    }

    /**
     * 添加音乐
     *
     * @param musicAddBo
     * @return
     */
    @Override
    public boolean add(MusicAddBo musicAddBo) {
        return false;
    }

    /**
     * 编辑音乐
     *
     * @param musicUpdateBo
     * @return
     */
    @Override
    public boolean updateMusic(MusicUpdateBo musicUpdateBo) {
        return false;
    }

    /**
     * 删除音乐
     *
     * @param musicId
     * @return
     */
    @Override
    public boolean deleteMusic(Integer musicId) {
        return false;
    }
}




