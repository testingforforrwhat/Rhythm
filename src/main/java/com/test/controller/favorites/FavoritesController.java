package com.test.controller.favorites;


import com.test.bean.bo.FavoritesAddBo;
import com.test.bean.bo.MusicAddBo;
import com.test.service.FavoritesService;
import com.test.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "收藏和分享模块")
@RequestMapping("/api")
@CrossOrigin		// 可以在支持跨域的方法或者类添加该注解
public class FavoritesController {


    // 依赖项
    @Autowired
    private FavoritesService favoritesService;



    /**
     * 查询获取当前用户收藏的音乐
     * GET http://127.0.0.1:8001/api/user/{user_id}
     * @return
     */
    @ResponseBody
    @ApiOperation("查询获取当前用户收藏的音乐")
    @GetMapping("/user/{user_id}")
    public Object getFavoritesById(
            @ApiParam("用户登录token令牌") @RequestHeader String authorization,
            @ApiParam(value = "用户ID",required = true)  @PathVariable("user_id") Integer user_id){
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的相关数据
//        data.put( "user_id" , favoritesService.selectByUserId(user_id) );
//        responseBody.put( "data" , data );
        return favoritesService.selectByUserId(user_id);
    }

    /**
     * 收藏音乐
     * Post http://127.0.0.1:8001/api/favorites
     * @param favoritesAddBo
     * @return
     */
    @ResponseBody
    @PostMapping("/favorites")
    @ApiOperation("收藏音乐")
    public Object addMusicAddBo(
            @ApiParam("用户登录token令牌") @RequestHeader String authorization,
            FavoritesAddBo favoritesAddBo){
        System.out.println( favoritesAddBo );
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的所有音乐分类数据
//        // 调用业务逻辑层 执行 添加音乐分类
//        if( favoritesService.add(favoritesAddBo) ) {
//            responseBody.put( "code" , 200 );
//            responseBody.put( "message" , "收藏音乐成功" );
//        }else{
//            responseBody.put( "code" , 500 );
//            responseBody.put( "message" , "收藏音乐失败" );
//        }
        // 返回 响应报文体
        return favoritesService.add(favoritesAddBo);
    }

    /**
     * 取消收藏音乐
     * Delete http://127.0.0.1:8001/api/favorites/{favorite_id}
     * @param favorite_id
     * @return
     */
    @ResponseBody
    @DeleteMapping("favorites/{favorite_id}")
    @ApiOperation("取消收藏音乐")
    public Object deleteMusicCategory(
            @ApiParam("用户登录token令牌") @RequestHeader String authorization,
            @ApiParam(value = "收藏ID",required = true)  @PathVariable("favorite_id") Integer favorite_id){
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 调用业务逻辑层 执行 删除音乐
//        if( favoritesService.deleteFavorites(favorite_id) ) {
//            responseBody.put( "code" , 200 );
//            responseBody.put( "message" , "取消收藏音乐成功" );
//        }else{
//            responseBody.put( "code" , 500 );
//            responseBody.put( "message" , "取消收藏音乐失败" );
//        }
        // 返回 响应报文体
        return favoritesService.deleteFavorites(favorite_id);
    }

}
