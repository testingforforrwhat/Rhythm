package com.test.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.bean.bo.MusicAddBo;
import com.test.bean.bo.MusicSearchBo;
import com.test.bean.bo.MusicUpdateBo;
import com.test.bean.po.Music;
import com.test.bean.po.MusicCategories;
import com.test.mapper.MusicCategoriesMapper;
import com.test.service.MusicService;
import com.test.mapper.MusicMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author 23194
* @description 针对表【music】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
    implements MusicService{


    // 依赖项
    @Resource
    private MusicMapper musicMapper;

    /**
     * 根据music_id查询对应信息
     *
     * @param musicId
     * @return
     */
    @Override
    public Object selectByMusicId(Integer musicId) {
        return musicMapper.selectById( musicId );
    }

    /**
     * 查询当前所有音乐
     *
     * @return
     */
    @Override
    public Object listAll() {
        return musicMapper.selectList( null );
    }

    /**
     * 添加音乐
     *
     * @param musicAddBo
     * @return
     */
    @Override
    public boolean add(MusicAddBo musicAddBo) {
        // 创建用于添加的Music对象
        Music music = new Music();
        // 借组属性拷贝工具将bo中的同名的属性值 赋给 music
        musicAddBo.setTitle( musicAddBo.getTitle() );
        musicAddBo.setArtist( musicAddBo.getArtist() );
        musicAddBo.setAlbum( musicAddBo.getAlbum() );
        musicAddBo.setCategoryId( musicAddBo.getCategoryId() );
        System.out.println( musicAddBo.getTitle() );


        BeanUtil.copyProperties(musicAddBo,music);
        System.out.println( music );
        return musicMapper.insert( music ) > 0 ? true : false;
    }

    /**
     * 编辑音乐
     *
     * @param musicUpdateBo
     * @return
     */
    @Override
    public boolean updateMusic(MusicUpdateBo musicUpdateBo) {

        System.out.println( musicUpdateBo );
        // 创建用于更新的UpdateWapper
        UpdateWrapper<Music> updateWrapper = new UpdateWrapper<>();
        // 设置需要更新的字段 和更新条件
        updateWrapper.set("title",musicUpdateBo.getTitle())
                .set("artist",musicUpdateBo.getArtist())
                .set("album",musicUpdateBo.getAlbum())
                .set("category_id",musicUpdateBo.getCategoryId())
                .eq("music_id",musicUpdateBo.getMusicId());

        System.out.println( "updateWrapper:" + updateWrapper );
        int update = musicMapper.update(new Music(), updateWrapper);
        System.out.println( "musicUpdateBo:" + musicUpdateBo );
        // 因为这个方法需要将更新过后的数据返回 所以可以再次查询数据库
        Music music = musicMapper.selectById(musicUpdateBo.getCategoryId());
        return update > 0 ? true : false;

    }

    /**
     * 删除音乐
     *
     * @param musicId
     * @return
     */
    @Override
    public boolean deleteMusic(Integer musicId) {
        return musicMapper.deleteById( musicId ) > 0 ? true : false;
    }

    /**
     * 根据 帅选条件、分页条件、排序条件，查询满足条件的音乐数据
     *
     * @param musicSearchBo 音乐搜索业务模型对象
     * @return 满足条件的音乐实体模型对象的集合
     */
    @Override
    public List<Music> listByBo(MusicSearchBo musicSearchBo) {

        // 使用PageHelper进行分页
        PageHelper.startPage( musicSearchBo.getPage(),musicSearchBo.getPageSize() );
//        List<Music> musicList = musicMapper.selectAll();
//        PageInfo<Music> pageInfo = new PageInfo<Music>(musicList);
//        System.out.println(musicList);
//        System.out.println(pageInfo);

        return musicMapper.selectList(null);
    }

}




