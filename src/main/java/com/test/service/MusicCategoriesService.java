package com.test.service;

import com.test.bean.po.MusicCategories;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23194
* @description 针对表【music_categories】的数据库操作Service
* @createDate 2024-04-03 12:32:12
*/
public interface MusicCategoriesService extends IService<MusicCategories> {

    /**
     * 根据分类ID，从数据库查询该分类数据
     * @param categoryId
     * @return
     */
    MusicCategories selectByCategoryId(Integer categoryId);
}
