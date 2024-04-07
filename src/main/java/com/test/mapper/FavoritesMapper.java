package com.test.mapper;

import com.test.bean.po.Favorites;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 23194
* @description 针对表【favorites】的数据库操作Mapper
* @createDate 2024-04-03 12:32:12
* @Entity com.test.bean.po.Favorites
*/
@Mapper
public interface FavoritesMapper extends BaseMapper<Favorites> {

}




