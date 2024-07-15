package com.test.controller.advertise;

import com.test.bean.po.Advertisements;
import com.test.service.AdvertisementsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * 根据ad_id查询广告信息
     */
    // 需要Admin角色权限
    // Spring Security 默认使用 ROLE_ 前缀来识别角色。
    // 因此，如果你的角色在数据库中存储的时候，已经包括了 ROLE_ 前缀，那么你在使用 @PreAuthorize 的时候也需要带上这个前缀，
    // 否则,无论是在数据库中还是在代码中，确保角色的命名保持一致。比如：ROLE_test 或者 test

    /**
     *
     * 如果你不在控制器方法上使用 @PreAuthorize 或权限注解（如 @Secured 或 @RolesAllowed），
     * 方法默认将对所有经过身份认证的用户开放访问。这意味着，只要用户成功通过认证（例如成功登录得到一个有效的 JWT），就可以访问该方法
     * @param ad_id
     * @return
     */
    @PreAuthorize("hasRole('用户')")
    @ResponseBody
    @ApiOperation("根据ad_id查询对应信息")
    @GetMapping("/ads/{ad_id}")
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
