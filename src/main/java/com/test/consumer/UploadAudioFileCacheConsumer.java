package com.test.consumer;

import com.test.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class UploadAudioFileCacheConsumer {

    @Resource
    private CacheService cacheService;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @KafkaListener(topics = "uploadAudioFileCacheDelete-topic", groupId = "my-group")
    public void consume(String key, Acknowledgment acknowledgment) {
        // 延迟处理消息，假设数据库主从同步时间为1秒，再加500毫秒
        long delay = 1500L;

        scheduler.schedule(() -> {
            try {
                // 再次删除缓存（保证双删）
                cacheService.deleteCache(key);
                // 确认消息
                acknowledgment.acknowledge();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, delay, TimeUnit.MILLISECONDS);
    }
}