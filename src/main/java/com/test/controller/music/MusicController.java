package com.test.controller.music;


import com.test.bean.bo.MusicAddBo;
import com.test.bean.bo.MusicCategoriesAddBo;
import com.test.bean.bo.MusicCategoriesUpdateBo;
import com.test.bean.bo.MusicUpdateBo;
import com.test.service.MusicCategoriesService;
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
@Api(tags = "音乐模块")
@RequestMapping("/api")
@CrossOrigin		// 可以在支持跨域的方法或者类添加该注解
public class MusicController {


    // 依赖项
    @Autowired
    private MusicService musicService;

    /**
     * @param music_id
     * @return GET http://127.0.0.1:8001/api/music/{music_id}
     * 根据music_id查询对应信息
     */
    @ResponseBody
    @ApiOperation("查询每个音乐")
    @GetMapping("/music/{music_id}")
    public Object getMusicById(@ApiParam(value = "音乐ID",required = true)  @PathVariable("music_id") Integer music_id){
        // 实例化 响应报文体
        Map<String,Object> responseBody = new HashMap<>();
        // 设置 响应报文体 参数
        responseBody.put( "code" , 200 );
        responseBody.put( "message" , "OK" );
        Map<String,Object> data = new HashMap<>();
        // 载荷 系统中的相关数据
        data.put( "music_id" , musicService.selectByMusicId(music_id) );
        responseBody.put( "data" , data );
        return responseBody;
    }

    /**
     * 查询当前所有音乐
     * GET http://127.0.0.1:8001/api/music
     * @return
     */
    @ResponseBody
    @ApiOperation("查询当前所有音乐")
    @GetMapping("/music")
    public Object list(){
        // 实例化 响应报文体
        Map<String,Object> responseBody = new HashMap<>();
        // 设置 响应报文体 参数
        responseBody.put( "code" , 200 );
        responseBody.put( "message" , "OK" );
        Map<String,Object> data = new HashMap<>();
        // 载荷 系统中的所有音乐数据
        data.put( "musicList" , musicService.listAll() );
        responseBody.put( "data" , data );
        // 返回 响应报文体
        return responseBody;
    }

    /**
     * 添加音乐
     * Post http://127.0.0.1:8001/api/music
     * @param musicAddBo
     * @return
     */
    @ResponseBody
    @PostMapping("/music")
    @ApiOperation("添加音乐")
    public Object addMusicAddBo( MusicAddBo musicAddBo){
        System.out.println( musicAddBo );
        // 实例化 响应报文体
        Map<String,Object> responseBody = new HashMap<>();
        // 设置 响应报文体 参数
        responseBody.put( "code" , 200 );
        responseBody.put( "message" , "OK" );
        Map<String,Object> data = new HashMap<>();
        // 载荷 系统中的所有音乐分类数据
        // 调用业务逻辑层 执行 添加音乐分类
        if( musicService.add(musicAddBo) ) {
            responseBody.put( "code" , 200 );
            responseBody.put( "message" , "添加音乐成功" );
        }else{
            responseBody.put( "code" , 500 );
            responseBody.put( "message" , "添加音乐失败" );
        }
        // 返回 响应报文体
        return responseBody;
    }


    /**
     * 编辑音乐
     * Patch http://127.0.0.1:8001/api/music
     * @param musicUpdateBo
     * @return
     */
    @ResponseBody
    @PatchMapping("/music")
    @ApiOperation("编辑音乐分类")
    public Object editMusicAddBo( MusicUpdateBo musicUpdateBo){
        System.out.println( musicUpdateBo );
        // 实例化 响应报文体
        Map<String,Object> responseBody = new HashMap<>();
        // 设置 响应报文体 参数
        responseBody.put( "code" , 200 );
        responseBody.put( "message" , "OK" );
        Map<String,Object> data = new HashMap<>();
        // 载荷 系统中的所有音乐分类数据
        // 调用业务逻辑层 执行 编辑音乐
        if( musicService.updateMusic( musicUpdateBo ) ) {
            responseBody.put( "code" , 200 );
            responseBody.put( "message" , "编辑音乐成功" );
        }else{
            responseBody.put( "code" , 500 );
            responseBody.put( "message" , "编辑音乐失败" );
        }
        // 返回 响应报文体
        return responseBody;
    }


    /**
     * 删除音乐
     * Delete http://127.0.0.1:8001/api/music/{music_id}
     * @param music_id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/music/{music_id}")
    @ApiOperation("删除音乐")
    public Object deleteMusicCategory( @ApiParam(value = "音乐ID",required = true)  @PathVariable("music_id") Integer music_id){
        // 实例化 响应报文体
        Map<String,Object> responseBody = new HashMap<>();
        // 设置 响应报文体 参数
        responseBody.put( "code" , 200 );
        responseBody.put( "message" , "OK" );
        Map<String,Object> data = new HashMap<>();
        // 调用业务逻辑层 执行 删除音乐
        if( musicService.deleteMusic(music_id) ) {
            responseBody.put( "code" , 200 );
            responseBody.put( "message" , "删除音乐成功" );
        }else{
            responseBody.put( "code" , 500 );
            responseBody.put( "message" , "删除音乐失败" );
        }
        // 返回 响应报文体
        return responseBody;
    }

}
