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


/**
 *
 * 实现消息队列消费者读取到消息后延迟一段时间再处理，可以使用以下几种方法：
 *
 * 线程睡眠：简单直接的方法是让当前线程睡眠一段时间。但不推荐生产环境使用，因为它会阻塞线程。
 * 调度任务：使用调度任务框架，如 ScheduledExecutorService 来调度任务。
 * 消息队列特性：某些消息队列（如 RabbitMQ 和 Kafka）本身支持延迟队列或延迟消息特性。
 *
 */
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