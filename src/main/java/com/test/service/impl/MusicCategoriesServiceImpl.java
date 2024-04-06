package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.MusicCategories;
import com.test.service.MusicCategoriesService;
import com.test.mapper.MusicCategoriesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 23194
* @description 针对表【music_categories】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class MusicCategoriesServiceImpl extends ServiceImpl<MusicCategoriesMapper, MusicCategories>
    implements MusicCategoriesService{


    // 依赖项
    @Resource
    private MusicCategoriesMapper musicCategoriesMapper;


    /**
     * 根据分类ID，从数据库查询该分类数据
     *
     * @param categoryId
     * @return
     */
    @Override
    public MusicCategories selectByCategoryId(Integer categoryId) {
        return musicCategoriesMapper.selectById( categoryId );
    }
}




