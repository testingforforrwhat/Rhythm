package com.test.service.impl;


import com.alibaba.fastjson2.JSONObject;
import com.test.service.LocationService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 定位业务逻辑层实现类
 * */
@Service
public class LocationServiceImpl implements LocationService {

    // 依赖的OkHttp操作帮助类
    @Bean
    private OkHttpClient okHttpClient(){
        return new OkHttpClient();
    }

    /**
     * ip地址归属丁定位
     * @return 定位的地区编号
     * */
    @Override
    public Integer ipLocation() throws IOException {

        // 步骤一：获取当前发送请求的客户端的IP地址
//        String ipAddress = getRequestIpAddress();               // 生产环境下，获取外网ip地址
        String ipAddress = "124.79.157.104";                    // 开发环境下，外网ip地址伪代码

        // 步骤二：向第三方IP地址归属地查询接口，发送请求
        // 2.1 申明 要调用的第三方接口的URL
        String url = "http://whois.pconline.com.cn/ipJson.jsp?ip=" + ipAddress +"&json=true";   // 正确的URL
//        String url = "http://whois.pconline.com.cn/error.jsp?ip=" + ipAddress +"&json=true";    // 错误的URL，为了演示第三方服务不可用
        System.out.println( url );
        // 2.2 使用 OKHttp组件，向第三接口发送请求
        Request okHttpRequest = new Request.Builder().url( url ).build();
        Response response =  okHttpClient().newCall( okHttpRequest ).execute();

        // 步骤三：解析响应报文体
        // 3.1 将 响应报文体 反序列化为 Map对象
        Map<String,String> responseBody = JSONObject.parseObject( response.body().string() , Map.class );
        // 3.2 返回 二级行政地区编号
        return Integer.parseInt( responseBody.get("cityCode") );
    }

    // 依赖项
    @Autowired private HttpServletRequest request;

    /**
     * 查询当前请求的用户的ip地址的方法
     * */
    private String getRequestIpAddress() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

}
