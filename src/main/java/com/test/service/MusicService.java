package com.test.service;

import com.test.bean.bo.MusicAddBo;
import com.test.bean.bo.MusicSearchBo;
import com.test.bean.bo.MusicUpdateBo;
import com.test.bean.po.Music;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
     * 编辑音乐, uploadAudioFileByMusicId, update `music_file`字段
     * @param music_id
     * @return
     */
    boolean updateMusic(String music_id);

    /**
     * 删除音乐
     * @param musicId
     * @return
     */
    boolean deleteMusic(Integer musicId);

    /**
     * 根据 帅选条件、分页条件、排序条件，查询满足条件的音乐数据
     * @param musicSearchBo 音乐搜索业务模型对象
     * @return 满足条件的音乐实体模型对象的集合
     * */
    List<Music> listByBo(MusicSearchBo musicSearchBo);

    byte[] loadAudioAsResource(String music_id);

    String playAudio(String music_id) throws IOException;

    byte[] convertAudioToBlob(File file) throws IOException;

    /**
     * uploadAudioFile
     * @param multipartFile
     * @return
     */
    String uploadAudioFile( MultipartFile multipartFile ) throws IOException;

}
