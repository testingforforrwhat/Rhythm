package com.test.controller.users;



import com.test.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/location")
@CrossOrigin        // 可以在支持跨域的方法或者类添加该注解
public class LocationController {

    // 依赖项
    @Autowired private LocationService locationService;

    /**
     * ip地址归属丁定位接口 正常服务
     * @return 响应报文体
     * */
    @GetMapping("/ip")
    public Object ipLocation() throws IOException {
        Map<String,Object> responseBody = new HashMap<>();
        responseBody.put( "code" , 200 );
        responseBody.put( "message" , "OK" );
        responseBody.put( "data" , locationService.ipLocation() );
        return responseBody;
    }



}
