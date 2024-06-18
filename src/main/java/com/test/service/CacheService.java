package com.test.service;

public interface CacheService {

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
     boolean deleteCache(String key);

}
