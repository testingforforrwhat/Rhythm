package com.test.service;

import com.test.bean.bo.MusicCategoriesAddBo;
import com.test.bean.bo.MusicCategoriesUpdateBo;
import com.test.bean.po.MusicCategories;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询当前所有音乐分类
     * @return
     */
    List<MusicCategories> listAll();

    /**
     * 添加音乐分类
     * @param musicCategoriesAddBo
     * @return
     */
    boolean add( MusicCategoriesAddBo musicCategoriesAddBo );

    /**
     * 编辑音乐分类
     * @param musicCategoriesUpdateBo
     * @return
     */
    boolean updateMusicCategories(MusicCategoriesUpdateBo musicCategoriesUpdateBo);

    /**
     * 删除音乐分类
     * @param categoryId
     * @return
     */
    boolean deleteMusicCategory(Integer categoryId);

}
