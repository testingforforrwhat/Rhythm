package com.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.test.controller.music.MusicController;
import com.test.service.CacheService;
import com.test.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CacheServiceImpl implements CacheService {


    @Resource
    private RedisUtil redisUtil;


    /**
     * 删除缓存
     *
     * @param music_id
     * @return
     */
    @Override
    public boolean deleteCache(Integer music_id) {

        // Redis Key 生成规则：方法签名+实参数据

        Class<MusicController> musicControllerClass = MusicController.class;

        String theParameter = String.valueOf(music_id);
        String[] numbers = {theParameter};

        Map<String,Object> keyMap = new HashMap<>();
        keyMap.put( "signature" , "String com.test.controller.music.MusicController.playAudio(String)" );
        keyMap.put( "arguments" , numbers );
        String key = JSON.toJSONString( keyMap );
        String key_redis_lock_Mutex = "redis_lock_Mutex-" + JSON.toJSONString( keyMap );
        System.out.println("待删除的redis key: " + key);

        // delete key
        redisUtil.del(key);
        System.out.println("Cache deleted Redis keys: " + key);
        redisUtil.del(key_redis_lock_Mutex);
        System.out.println("Cache deleted Redis keys: " + key_redis_lock_Mutex);

        return false;
    }


}
