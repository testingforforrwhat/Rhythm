package com.test.service;

import com.test.bean.bo.FavoritesAddBo;
import com.test.bean.bo.MusicAddBo;
import com.test.bean.po.Favorites;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23194
* @description 针对表【favorites】的数据库操作Service
* @createDate 2024-04-03 12:32:12
*/
public interface FavoritesService extends IService<Favorites> {


    /**
     * 收藏音乐
     * @param favoritesAddBo
     * @return
     */
    boolean add(FavoritesAddBo favoritesAddBo);

    /**
     * 取消收藏音乐
     * @param favoriteId
     * @return
     */
    boolean deleteMusic(Integer favoriteId);

    /**
     * 查询获取当前用户收藏的音乐
     * @return
     */
    Object selectByUserId(Integer userId);
}
