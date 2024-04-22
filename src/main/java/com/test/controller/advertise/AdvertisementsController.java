package com.test.controller.advertise;

import com.test.bean.po.Advertisements;
import com.test.service.AdvertisementsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "广告模块")
@RequestMapping("/api")
public class AdvertisementsController {


    // 依赖项
    @Autowired
    private AdvertisementsService advertisementsService;

    /**
     * @param ad_id
     * @return GET http://127.0.0.1:8001/api/ads/postAds/{ad_id}
     * 根据ad_id查询对应信息
     */
    @ResponseBody
    @ApiOperation("根据ad_id查询对应信息")
    @GetMapping("/ads/postAds/{ad_id}")
    public Object getPostAdsById(@ApiParam(value = "广告ID",required = true)  @PathVariable("ad_id") Integer ad_id){
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 根据rhythm.advertisements表的字段查询rhythm.advertisements表
//        // 载荷 系统中的相关数据
//        data.put( "ad" , advertisementsService.selectByAdId(ad_id) );
//        responseBody.put( "data" , data );
        return advertisementsService.selectByAdId(ad_id);
    }

}
