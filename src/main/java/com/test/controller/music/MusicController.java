package com.test.controller.music;


import com.test.bean.bo.*;
import com.test.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

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
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的相关数据
//        data.put( "music_id" , musicService.selectByMusicId(music_id) );
//        responseBody.put( "data" , data );
        return musicService.selectByMusicId(music_id);
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
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的所有音乐数据
//        data.put( "musicList" , musicService.listAll() );
//        responseBody.put( "data" , data );
        // 返回 响应报文体
        return musicService.listAll();
    }

    /**
     * 查询满足条件的所有音乐
     * Post http://127.0.0.1:8001/api/music
     * @return
     */
    @ResponseBody
    @ApiOperation("根据 筛选条件、分页条件、排序条件，查询满足条件的音乐数据")
    @PostMapping("/music/search")
    public Object listByBo(MusicSearchBo musicSearchBo){
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的所有音乐数据
//        data.put( "musicList" , musicService.listAll() );
//        responseBody.put( "data" , data );
        // 返回 响应报文体
        return musicService.listByBo( musicSearchBo );
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
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的所有音乐分类数据
//        // 调用业务逻辑层 执行 添加音乐分类
//        if( musicService.add(musicAddBo) ) {
//            responseBody.put( "code" , 200 );
//            responseBody.put( "message" , "添加音乐成功" );
//        }else{
//            responseBody.put( "code" , 500 );
//            responseBody.put( "message" , "添加音乐失败" );
//        }
        // 返回 响应报文体
        return musicService.add(musicAddBo);
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
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的所有音乐分类数据
//        // 调用业务逻辑层 执行 编辑音乐
//        if( musicService.updateMusic( musicUpdateBo ) ) {
//            responseBody.put( "code" , 200 );
//            responseBody.put( "message" , "编辑音乐成功" );
//        }else{
//            responseBody.put( "code" , 500 );
//            responseBody.put( "message" , "编辑音乐失败" );
//        }
        // 返回 响应报文体
        return  musicService.updateMusic( musicUpdateBo );
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
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 调用业务逻辑层 执行 删除音乐
//        if( musicService.deleteMusic(music_id) ) {
//            responseBody.put( "code" , 200 );
//            responseBody.put( "message" , "删除音乐成功" );
//        }else{
//            responseBody.put( "code" , 500 );
//            responseBody.put( "message" , "删除音乐失败" );
//        }
        // 返回 响应报文体
        return musicService.deleteMusic(music_id);
    }

    @GetMapping("/audio/{music_id}")
    public ResponseEntity<byte[]> getAudio(@PathVariable String music_id) throws IOException {
        File file = musicService.loadAudioAsResource(music_id);
        System.out.println(file);
        if (file != null) {
            // 实例化响应报文头对象
            HttpHeaders headers = new HttpHeaders();
            // 设置响应报文头，指示浏览器以流式方式播放音频
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            // 返回响应报文
            return new ResponseEntity<>(
                    FileUtils.readFileToByteArray(file),  // 响应报文体
                    headers,                              // 响应报文头
                    HttpStatus.OK                          // 响应状态
            );
        }
        return ResponseEntity.notFound().build();
    }

}
