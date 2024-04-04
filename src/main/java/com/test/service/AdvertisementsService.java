package com.test.service;

import com.test.bean.po.Advertisements;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 23194
* @description 针对表【advertisements】的数据库操作Service
* @createDate 2024-04-03 12:07:01
*/
public interface AdvertisementsService extends IService<Advertisements> {

    List<Advertisements> selectAdList(Integer ad_id);

}
