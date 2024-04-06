package com.test.mapper;

import com.test.bean.po.Advertisements;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 23194
* @description 针对表【advertisements】的数据库操作Mapper
* @createDate 2024-04-03 12:07:01
* @Entity com.test.bean.po.Advertisements
*/
public interface AdvertisementsMapper extends BaseMapper<Advertisements> {

    Advertisements selectByAdId( @Param("ad_id") Integer ad_id);
}




