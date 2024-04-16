package com.test.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.bo.FavoritesAddBo;
import com.test.bean.bo.MusicAddBo;
import com.test.bean.po.Favorites;
import com.test.bean.po.Music;
import com.test.mapper.MusicMapper;
import com.test.service.FavoritesService;
import com.test.mapper.FavoritesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 23194
* @description 针对表【favorites】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites>
    implements FavoritesService{


    // 依赖项
    @Resource
    private FavoritesMapper favoritesMapper;


    /**
     * 查询获取当前用户收藏的音乐
     *
     * @param userId
     * @return
     */
    @Override
    public List<Favorites> selectByUserId(Integer userId) {
        return favoritesMapper.selectByUserId( userId );
    }

    /**
     * 收藏音乐
     *
     * @param favoritesAddBo
     * @return
     */
    @Override
    public boolean add(FavoritesAddBo favoritesAddBo) {
        // 创建用于添加的Favorites对象
        Favorites favorites = new Favorites();
        // 借组属性拷贝工具将bo中的同名的属性值 赋给 favorites
        favoritesAddBo.setMusicId( favoritesAddBo.getMusicId() );
        favoritesAddBo.setUserId( favoritesAddBo.getUserId() );
        System.out.println( favoritesAddBo.getMusicId() );


        BeanUtil.copyProperties(favoritesAddBo,favorites);
        System.out.println( favorites );
        return favoritesMapper.insert( favorites ) > 0 ? true : false;
    }

    /**
     * 取消收藏音乐
     *
     * @param favoriteId
     * @return
     */
    @Override
    public boolean deleteFavorites(Integer favoriteId) {
        return favoritesMapper.deleteById(favoriteId) > 0 ? true : false;
    }


}




