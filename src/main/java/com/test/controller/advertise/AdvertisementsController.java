package com.test.controller.advertise;

import com.test.service.AdvertisementsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(tags = "广告模块")
@RequestMapping("/api/ads")
public class AdvertisementsController {


    // 依赖项
    @Autowired
    private AdvertisementsService advertisementsService;

    /**
     *
     * @param ad_id
     * @return
     *
     * GET http://127.0.0.1:8001/api/ads/postAds/{ad_id}
     * 根据ad_id查询对应信息
     *
     */
    @ResponseBody
    @ApiOperation("发布广告")
    @GetMapping("/postAds/{ad_id}")
    public String index( @ApiParam(value = "广告ID",required = true)  @PathVariable("ad_id") String ad_id){
        return advertisementsService.selectAdList( Integer.valueOf(ad_id) ).toString();
    }

}
