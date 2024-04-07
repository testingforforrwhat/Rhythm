package com.test.mapper;

import com.test.bean.bo.MusicCategoriesAddBo;
import com.test.bean.po.MusicCategories;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 23194
* @description 针对表【music_categories】的数据库操作Mapper
* @createDate 2024-04-03 12:32:12
* @Entity com.test.bean.po.MusicCategories
*/
@Mapper
public interface MusicCategoriesMapper extends BaseMapper<MusicCategories> {

    /**
     * 添加
     * @param musicCategoriesAddBo
     * @return
     */
    int add( @Param( "musicCategoriesAddBo" ) MusicCategoriesAddBo musicCategoriesAddBo);

}




