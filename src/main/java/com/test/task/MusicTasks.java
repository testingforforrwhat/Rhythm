package com.test.task;

import com.test.bean.po.Music;
import com.test.mapper.MusicMapper;
import com.test.utils.RedisUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@Component
public class MusicTasks {


    // 依赖项
    @Resource
    private MusicMapper musicMapper;

    @Resource
    private RedisUtil redisUtil;

    @Scheduled(fixedRate = 60000) // 每分钟运行一次
    @Transactional
    public void updatePlayCounts() {
        // 获取与指定模式匹配的所有键
        // 假设所有相关的键都以 "audio:playcount:" 开头
        Set<String> keys = redisUtil.keys("audio:playcountByWeekByMusicId:*");
        if (keys != null) {
            for (String key : keys) {
                // 从 Redis 获取播放次数
                Integer playCountCurrentWeek = (Integer) redisUtil.get(key);
                if (playCountCurrentWeek != null) {
                    // 从 key 中提取 filename
                    String music_id = key.replace("audio:playcountByWeekByMusicId:", "");
                    System.out.println(music_id);

                    // 更新数据库
                    Music audioFile = musicMapper.selectById(music_id);
                    if (audioFile != null) {
                        audioFile.setMusicPlayCountWeek(playCountCurrentWeek);
                        musicMapper.updateById(audioFile);
                    }
                }
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * *") // 每天凌晨执行
    @Transactional
    public void setTopTenPlayCache() throws IOException {

        // 查询有序集合中计数最高的前十个元素（歌曲）
        Set<ZSetOperations.TypedTuple<Object>> topTenSongs = redisUtil.reverseRangeWithScores("audio:topSongsByPlaycount", 0, 1);

        System.out.println(topTenSongs);

        // 处理每首歌曲
        for (ZSetOperations.TypedTuple<Object> tuple : topTenSongs) {
            String songName = new String(String.valueOf(tuple.getValue()));
            System.out.println("Storing song: " + songName + ", Count: " + tuple.getScore());

            // 指定要播放的音频文件
            String filename = songName;
            System.out.println(filename);
            File file = new File(
                    ResourceUtils.getURL("classpath:").getPath() +
                            "static/audio/" + filename
            );
            System.out.println(file);

            byte[] bytes = FileUtils.readFileToByteArray(file);
            redisUtil.set("audio:file:playcountByWeekByMusicId:" + songName, bytes);



        }
    }

    /**
     *
     * 每周定时清除redis相关key,数据累加到sum
     */
    @Scheduled(cron = "0 0 0 * * MON") // 每周一凌晨执行
    @Transactional
    public void PlayCountByWeekCleanupTask() {
        // 获取与指定模式匹配的所有键
        // 假设所有相关的键都以 "audio:playcount:" 开头
        Set<String> keysToDelete = redisUtil.keys("audio:playcountByWeekByMusicId:*");
        if (!keysToDelete.isEmpty()) {
            for (String key : keysToDelete) {
                // 从 Redis 获取播放次数
                Integer playCountCurrentWeek = (Integer) redisUtil.get(key);
                Integer playCount = null;
                if (playCountCurrentWeek != null) {
                    // 从 key 中提取 filename
                    String music_id = key.replace("audio:playcountByWeekByMusicId:", "");
                    System.out.println(music_id);

                    // 更新数据库
                    Music audioFile = musicMapper.selectById(music_id);
                    playCount = playCountCurrentWeek + audioFile.getMusicPlayCount();
                    if (audioFile != null) {
                        audioFile.setMusicPlayCountWeek(playCountCurrentWeek);
                        audioFile.setMusicPlayCount(playCount);
                        musicMapper.updateById(audioFile);
                    }
                    // delete key
                    redisUtil.del(key);
                    System.out.println("Cleared Redis keys: " + key);
                }
            }
        }
        else {
            System.out.println("No keys found to clear.");
        }

        // 获取与指定模式匹配的所有键
        // 假设所有相关的键都以 "audio:file:playcountByWeekByMusicId:" 开头
        Set<String> keysTopTenCacheToDelete = redisUtil.keys("audio:file:playcountByWeekByMusicId:*");
        if (!keysTopTenCacheToDelete.isEmpty()) {
            for (String key : keysTopTenCacheToDelete) {

                // delete key
                redisUtil.del(key);
                System.out.println("Cleared TopTenCache Redis keys: " + key);

            }
        }
        else {
            System.out.println("No TopTenCache keys found to clear.");
        }

        // 获取与指定模式匹配的所有键
        // 假设所有相关的键都以 "audio:topSongsByPlaycount" 开头
        Set<String> keysTopSongsByPlaycountToDelete = redisUtil.keys("audio:topSongsByPlaycount");
        if (!keysTopSongsByPlaycountToDelete.isEmpty()) {
            for (String key : keysTopSongsByPlaycountToDelete) {

                // delete key
                redisUtil.del(key);
                System.out.println("Cleared TopSongsByPlaycount Redis keys: " + key);

            }
        }
        else {
            System.out.println("No TopSongsByPlaycount keys found to clear.");
        }
    }

}