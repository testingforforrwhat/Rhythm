package com.test.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.test.bean.bo.MusicAddBo;
import com.test.bean.bo.MusicSearchBo;
import com.test.bean.bo.MusicUpdateBo;
import com.test.bean.po.Music;
import com.test.service.MusicService;
import com.test.mapper.MusicMapper;
import com.test.utils.RedisUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Set;

/**
* @author 23194
* @description 针对表【music】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
    implements MusicService{


    // 依赖项
    @Resource
    private MusicMapper musicMapper;

    @Resource
    private RedisUtil redisUtil;


    /**
     * 根据music_id查询对应信息
     *
     * @param musicId
     * @return
     */
    @Override
    public Object selectByMusicId(Integer musicId) {
        return musicMapper.selectById( musicId );
    }

    /**
     * 查询当前所有音乐
     *
     * @return
     */
    @Override
    public Object listAll() {
        return musicMapper.selectList( null );
    }

    /**
     * 添加音乐
     *
     * @param musicAddBo
     * @return
     */
    @Override
    public boolean add(MusicAddBo musicAddBo) {
        // 创建用于添加的Music对象
        Music music = new Music();
        // 借组属性拷贝工具将bo中的同名的属性值 赋给 music
        musicAddBo.setTitle( musicAddBo.getTitle() );
        musicAddBo.setArtist( musicAddBo.getArtist() );
        musicAddBo.setAlbum( musicAddBo.getAlbum() );
        musicAddBo.setCategoryId( musicAddBo.getCategoryId() );
        System.out.println( musicAddBo.getTitle() );


        BeanUtil.copyProperties(musicAddBo,music);
        System.out.println( music );
        return musicMapper.insert( music ) > 0 ? true : false;
    }

    /**
     * 编辑音乐
     *
     * @param musicUpdateBo
     * @return
     */
    @Override
    public boolean updateMusic(MusicUpdateBo musicUpdateBo) {

        System.out.println( musicUpdateBo );
        // 创建用于更新的UpdateWapper
        UpdateWrapper<Music> updateWrapper = new UpdateWrapper<>();
        // 设置需要更新的字段 和更新条件
        updateWrapper.set("title",musicUpdateBo.getTitle())
                .set("artist",musicUpdateBo.getArtist())
                .set("album",musicUpdateBo.getAlbum())
                .set("category_id",musicUpdateBo.getCategoryId())
                .eq("music_id",musicUpdateBo.getMusicId());

        System.out.println( "updateWrapper:" + updateWrapper );
        int update = musicMapper.update(new Music(), updateWrapper);
        System.out.println( "musicUpdateBo:" + musicUpdateBo );
        // 因为这个方法需要将更新过后的数据返回 所以可以再次查询数据库
        Music music = musicMapper.selectById(musicUpdateBo.getCategoryId());
        return update > 0 ? true : false;

    }

    /**
     * 删除音乐
     *
     * @param musicId
     * @return
     */
    @Override
    public boolean deleteMusic(Integer musicId) {
        return musicMapper.deleteById( musicId ) > 0 ? true : false;
    }

    /**
     * 根据 帅选条件、分页条件、排序条件，查询满足条件的音乐数据
     *
     * @param musicSearchBo 音乐搜索业务模型对象
     * @return 满足条件的音乐实体模型对象的集合
     */
    @Override
    public List<Music> listByBo(MusicSearchBo musicSearchBo) {

        // 使用PageHelper进行分页
        PageHelper.startPage( musicSearchBo.getPage(),musicSearchBo.getPageSize() );
//        List<Music> musicList = musicMapper.selectAll();
//        PageInfo<Music> pageInfo = new PageInfo<Music>(musicList);
//        System.out.println(musicList);
//        System.out.println(pageInfo);

        return musicMapper.listByBo(musicSearchBo);
    }

    @Override
    public ResponseEntity<byte[]> loadAudioAsResource(String music_id) {
        String filename = null;
        try {
            // 指定要播放的音频文件
            filename = musicMapper.selectById(music_id).getMusicFile();
            System.out.println(filename);
            File file = new File(
                    ResourceUtils.getURL("classpath:").getPath() +
                            "static/audio/" + filename
            );
            System.out.println(file);
            if (file.exists()) {


                incrementPlayCount(music_id);

                ZSetOperations<String, Object> zSetOps = redisUtil.zSet();
                zSetOps.add("audio:topSongsByPlaycount", filename, (Integer) redisUtil.get("audio:playcountByWeekByMusicId:" + music_id));


                // 实例化响应报文头对象
                HttpHeaders headers = new HttpHeaders();
                // 设置响应报文头，指示浏览器以流式方式播放音频
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                System.out.println(headers);
                System.out.println(HttpStatus.OK);

                // 返回响应报文
                return new ResponseEntity<>(
                        FileUtils.readFileToByteArray(file),  // 响应报文体
                        headers,                              // 响应报文头
                        HttpStatus.OK                          // 响应状态
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading file " + filename, e);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public byte[] convertAudioToBlob(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream.close();
        byteArrayOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    private void incrementPlayCount(String music_id) {
        redisUtil.incr("audio:playcountByWeekByMusicId:" + music_id,1);
    }

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




