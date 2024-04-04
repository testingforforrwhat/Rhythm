package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.Advertisements;
import com.test.service.AdvertisementsService;
import com.test.mapper.AdvertisementsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 23194
* @description 针对表【advertisements】的数据库操作Service实现
* @createDate 2024-04-03 12:07:01
*/
@Service
public class AdvertisementsServiceImpl extends ServiceImpl<AdvertisementsMapper, Advertisements>
    implements AdvertisementsService{

    @Autowired
    private AdvertisementsMapper advertisementsMapper;

    /**
     *
     * GET http://127.0.0.1:8001/api/ads/postAds/{ad_id}
     * 根据ad_id查询对应信息
     *
     */
    @Override
    public List<Advertisements> selectAdList(Integer ad_id) {

        // 根据rhythm.advertisements表的字段查询rhythm.advertisements表
        return advertisementsMapper.selectListByAdId(ad_id);
    }

}




