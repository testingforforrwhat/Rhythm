package com.test.controller.musicCategories;


import com.test.bean.bo.MusicCategoriesUpdateBo;
import com.test.service.AdvertisementsService;
import com.test.service.MusicCategoriesService;
import com.test.bean.bo.MusicCategoriesAddBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "音乐管理模块")
@RequestMapping("/api")
@CrossOrigin		// 可以在支持跨域的方法或者类添加该注解
public class MusicCategoriesController {


    // 依赖项
    @Autowired
    private MusicCategoriesService musicCategoriesService;

    /**
     * @param category_id
     * @return GET http://127.0.0.1:8001/api/categories/query/{category_id}
     * 根据ad_id查询对应信息
     */
    @ResponseBody
    @ApiOperation("查询每个音乐分类")
    @GetMapping("/categories/query/{category_id}")
    public Object getMusicCategoriesById(@ApiParam(value = "分类ID",required = true)  @PathVariable("category_id") Integer category_id){
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的相关数据
//        data.put( "category_id" , musicCategoriesService.selectByCategoryId(category_id) );
//        responseBody.put( "data" , data );
        return musicCategoriesService.selectByCategoryId(category_id);
    }

    /**
     * 查询当前所有音乐分类
     * GET http://127.0.0.1:8001/api/categories/queryAll
     * @return
     */
    @ResponseBody
    @ApiOperation("查询当前所有音乐分类")
    @GetMapping("/categories/queryAll")
    public Object list(){
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的所有音乐分类数据
//        data.put( "musicCategoriesList" , musicCategoriesService.listAll() );
//        responseBody.put( "data" , data );
        // 返回 响应报文体
        return musicCategoriesService.listAll();
    }

    /**
     * 添加音乐分类
     * Post http://127.0.0.1:8001/api/categories/add
     * @param musicCategoriesAddBo
     * @return
     */
    @ResponseBody
    @PostMapping("/categories/add")
    @ApiOperation("添加音乐分类")
    public Object addMusicCategoriesAddBo( @RequestBody MusicCategoriesAddBo musicCategoriesAddBo){
        System.out.println( musicCategoriesAddBo );
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的所有音乐分类数据
//        // 调用业务逻辑层 执行 添加音乐分类
//        if( musicCategoriesService.add(musicCategoriesAddBo) ) {
//            responseBody.put( "code" , 200 );
//            responseBody.put( "message" , "添加音乐分类成功" );
//        }else{
//            responseBody.put( "code" , 500 );
//            responseBody.put( "message" , "添加音乐分类失败" );
//        }
        // 返回 响应报文体
        return  musicCategoriesService.add(musicCategoriesAddBo);
    }


    /**
     * 编辑音乐分类
     * Patch http://127.0.0.1:8001/api/categories/update
     * @param musicCategoriesUpdateBo
     * @return
     */
    @ResponseBody
    @PatchMapping("/categories/update")
    @ApiOperation("编辑音乐分类")
    public Object editMusicCategoriesAddBo( MusicCategoriesUpdateBo musicCategoriesUpdateBo){
        System.out.println( musicCategoriesUpdateBo );
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 载荷 系统中的所有音乐分类数据
//        // 调用业务逻辑层 执行 编辑音乐分类
//        if( musicCategoriesService.updateMusicCategories( musicCategoriesUpdateBo ) ) {
//            responseBody.put( "code" , 200 );
//            responseBody.put( "message" , "编辑音乐分类成功" );
//        }else{
//            responseBody.put( "code" , 500 );
//            responseBody.put( "message" , "编辑音乐分类失败" );
//        }
        // 返回 响应报文体
        return musicCategoriesService.updateMusicCategories( musicCategoriesUpdateBo );
    }


    /**
     * 删除音乐分类
     * Delete http://127.0.0.1:8001/api/categories/delete/{category_id}
     * @param category_id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/categories/delete/{category_id}")
    @ApiOperation("删除音乐分类")
    public Object deleteMusicCategory( @ApiParam(value = "分类ID",required = true)  @PathVariable("category_id") Integer category_id){
//        // 实例化 响应报文体
//        Map<String,Object> responseBody = new HashMap<>();
//        // 设置 响应报文体 参数
//        responseBody.put( "code" , 200 );
//        responseBody.put( "message" , "OK" );
//        Map<String,Object> data = new HashMap<>();
//        // 调用业务逻辑层 执行 编辑音乐分类
//        if( musicCategoriesService.deleteMusicCategory(category_id) ) {
//            responseBody.put( "code" , 200 );
//            responseBody.put( "message" , "删除音乐分类成功" );
//        }else{
//            responseBody.put( "code" , 500 );
//            responseBody.put( "message" , "删除音乐分类失败" );
//        }
        // 返回 响应报文体
        return musicCategoriesService.deleteMusicCategory(category_id);
    }

}
