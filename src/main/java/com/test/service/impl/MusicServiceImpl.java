package com.test.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.test.bean.bo.MusicAddBo;
import com.test.bean.bo.MusicSearchBo;
import com.test.bean.bo.MusicUpdateBo;
import com.test.bean.po.Music;
import com.test.controller.music.MusicController;
import com.test.service.CacheService;
import com.test.service.MusicService;
import com.test.mapper.MusicMapper;
import com.test.utils.AudioParserUtils;
import com.test.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
* @author 23194
* @description 针对表【music】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
    implements MusicService {


    // 依赖项
    @Resource
    private MusicMapper musicMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private AudioParserUtils audioParserUtils;

    @Resource
    private CacheService cacheService;

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 根据music_id查询对应信息
     *
     * @param musicId
     * @return
     */
    @Override
    public Object selectByMusicId(Integer musicId) {
        return musicMapper.selectById(musicId);
    }

    /**
     * 查询当前所有音乐
     *
     * @return
     */
    @Override
    public Object listAll() {
        return musicMapper.selectList(null);
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
        musicAddBo.setTitle(musicAddBo.getTitle());
        musicAddBo.setArtist(musicAddBo.getArtist());
        musicAddBo.setAlbum(musicAddBo.getAlbum());
        musicAddBo.setCategoryId(musicAddBo.getCategoryId());
        System.out.println(musicAddBo.getTitle());


        BeanUtil.copyProperties(musicAddBo, music);
        System.out.println(music);
        return musicMapper.insert(music) > 0 ? true : false;
    }

    /**
     * 编辑音乐
     *
     * @param musicUpdateBo
     * @return
     */
    @Override
    public boolean updateMusic(MusicUpdateBo musicUpdateBo) {

        System.out.println(musicUpdateBo);
        // 创建用于更新的UpdateWapper
        UpdateWrapper<Music> updateWrapper = new UpdateWrapper<>();
        // 设置需要更新的字段 和更新条件
        updateWrapper.set("title", musicUpdateBo.getTitle())
                .set("artist", musicUpdateBo.getArtist())
                .set("album", musicUpdateBo.getAlbum())
                .set("category_id", musicUpdateBo.getCategoryId())
                .eq("music_id", musicUpdateBo.getMusicId());

        System.out.println("updateWrapper:" + updateWrapper);
        int update = musicMapper.update(new Music(), updateWrapper);
        System.out.println("musicUpdateBo:" + musicUpdateBo);
        // 因为这个方法需要将更新过后的数据返回 所以可以再次查询数据库
        Music music = musicMapper.selectById(musicUpdateBo.getCategoryId());
        return update > 0 ? true : false;

    }

    /**
     * 编辑音乐, uploadAudioFileByMusicId, update `music_file`字段
     *
     * @param music_id
     * @return
     */
    @Override
    public boolean updateMusic(Integer music_id, String filename) {

        System.out.println(music_id);
        // 创建用于更新的UpdateWapper
        UpdateWrapper<Music> updateWrapper = new UpdateWrapper<>();
        // 设置需要更新的字段 和更新条件
        updateWrapper.set("music_file", filename)
                .eq("music_id", music_id);

        System.out.println("updateWrapper:" + updateWrapper);
        int update = musicMapper.update(new Music(), updateWrapper);
        System.out.println("update `music_file`字段:" + filename);
        // 因为这个方法需要将更新过后的数据返回 所以可以再次查询数据库
        Music music = musicMapper.selectById(music_id);
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
        return musicMapper.deleteById(musicId) > 0 ? true : false;
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
        PageHelper.startPage(musicSearchBo.getPage(), musicSearchBo.getPageSize());
//        List<Music> musicList = musicMapper.selectAll();
//        PageInfo<Music> pageInfo = new PageInfo<Music>(musicList);
//        System.out.println(musicList);
//        System.out.println(pageInfo);

        return musicMapper.listByBo(musicSearchBo);
    }

    @Override
    public byte[] loadAudioAsResource(String music_id) {
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


                audioParserUtils.incrementPlayCount(music_id);

                ZSetOperations<String, Object> zSetOps = redisUtil.zSet();
                zSetOps.add("audio:topSongsByPlaycount", filename, (Integer) redisUtil.get("audio:playcountByWeekByMusicId:" + music_id));


                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                System.out.println("audioStream: " + audioStream);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioInputStream);
//                clip.start();

                return audioStream.readAllBytes();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading file " + filename, e);
        }
        return null;
    }

    @Override
    public String playAudio(String music_id) throws IOException {
        String filename = null;
        try {
            // 指定要播放的音频文件
            filename = musicMapper.selectById(music_id).getMusicFile();
            System.out.println(filename);

            String filePath = ResourceUtils.getURL("classpath:").getPath() +
                    "static/audio/" + filename;
            // 绝对路径前面多了一个/ 去除
            // 确保 filePath 不以斜杠开头
            String fileNewPath = null;
            if (filePath.startsWith("/")) {
                fileNewPath = filePath.substring(1);
            } else if (filePath.startsWith("file:") ) {
                fileNewPath = filePath.replaceFirst("^file:", "");
            } else {
                fileNewPath = filePath;
            }
            System.out.println("fileNewPath: " + fileNewPath);
            Path audioFilePath = Paths.get(fileNewPath);

            // 创建文件对象
            File file = new File(
                    fileNewPath
            );
            System.out.println(file);


            // 使用 ResourceLoader 来加载资源
            org.springframework.core.io.Resource resource = resourceLoader.getResource(fileNewPath);
            System.out.println("org.springframework.core.io.Resource: " + resource);

            // 获取资源的输入流
            // springboot 打 jar 包后读取不到文件。这通常是因为 JAR 包本质上是一个压缩文件，
            // 如果我们直接用文件系统路径读文件，而不是使用类路径读取，就会发生该问题。
            // 我们将使用 ClassLoader.getResourceAsStream 方法读取资源文件。这样即便资源被打包在 JAR 文件内，也能够正常读取。
            /**
             *
             * caution: 资源路径应当是相对classpath路径(类路径）的路径。
             * 例如，如果 src/main/resources/static/test.wav ，那么你应该使用 static/test.wav 。
             *
             * https://blog.csdn.net/qq_16992475/article/details/123014242
             * https://zhuanlan.zhihu.com/p/642832661
             * https://zhuanlan.zhihu.com/p/513229512
             * https://cloud.tencent.com/developer/article/1885522
             * https://blog.csdn.net/laodanqiu/article/details/130563161
             * https://blog.csdn.net/marakaih/article/details/137825573
             *
             */
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/audio/" + filename);
            System.out.println("inputStream: " + inputStream);

            if (inputStream != null || file.exists()) {

                System.out.println("文件存在");

                audioParserUtils.incrementPlayCount(music_id);

                ZSetOperations<String, Object> zSetOps = redisUtil.zSet();
                zSetOps.add("audio:topSongsByPlaycount", filename, (Integer) redisUtil.get("audio:playcountByWeekByMusicId:" + music_id));

                return Arrays.toString(inputStream.readAllBytes());
            } else {
                System.out.println("File does not exist.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error loading file " + filename, e);
        }
        return null;
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

    /**
     * uploadAudioFile
     *
     * @param multipartFile
     * @return
     */
    @Override
    public String uploadAudioFile(MultipartFile multipartFile) throws IOException {

        System.out.println( "参数名称 = " + multipartFile.getName() );
        System.out.println( "文件类型 = " + multipartFile.getContentType() );
        System.out.println( "原文件名 = " + multipartFile.getOriginalFilename() );
        System.out.println( "文件大小 = " + multipartFile.getSize() );

        // 判断 上传的文件类型 是否是音频文件
        if( ! multipartFile.getContentType().startsWith("audio/") ){
            System.out.println( "==> 上传的文件不是音频文件，上传失败" );
            return null;
        }

        // 判断 上传的文件尺寸
        if( multipartFile.getSize() > 20 * 1024 * 1024 ){
            System.out.println( "==> 上传的文件最多20MB，上传失败" );
            return null;
        }

        // 给 上传文件 生成服务器端 文件名
        String filename = UUID.randomUUID().toString() +
                multipartFile.getOriginalFilename().substring( multipartFile.getOriginalFilename().lastIndexOf(".") );


        String filePath = ResourceUtils.getURL("classpath:").getPath() +
                "static/audio/" + filename;
        // 绝对路径前面多了一个/ 去除
        // 确保 filePath 不以斜杠开头
        String fileNewPath = null;
        if (filePath.startsWith("/")) {
            fileNewPath = filePath.substring(1);
        } else if (filePath.startsWith("file:") ) {
            fileNewPath = filePath.replaceFirst("^file:", "");
        } else {
            fileNewPath = filePath;
        }
        System.out.println("fileNewPath: " + fileNewPath);

        // 指定 转移目标文件
        File target = new File(fileNewPath);

        // 将 上传文件 从临时目录 转移到 目标文件夹
//        multipartFile.transferTo( target );

        // 上传的文件保存在 src/main/resources/static/audio/ 目录
        String currentDirectory = System.getProperty("user.dir");  // 当前工作目录是指程序启动时所在的目录
        System.out.println("currentDirectory: " + currentDirectory);
        String targetLocalDirectory = currentDirectory + "/src/main/resources/static/audio/" + filename;
        System.out.println("targetLocalDirectory: " + targetLocalDirectory);
        multipartFile.transferTo(new File(targetLocalDirectory));

        return "File upload successful: " + fileNewPath +", " + targetLocalDirectory;

    }

    /**
     * uploadAudioFileByMusicId
     *
     * @param multipartFile
     * @param music_id
     * @return
     */
    @Override
    public Object uploadAudioFileByMusicId( MultipartFile multipartFile, Integer music_id) throws IOException, NoSuchMethodException {

        /**
         *
         * **第一次删除缓存**：在更新数据库之前删除缓存。
         *
         */

        // 步骤一：去Redis中删除缓存数据

        System.out.println("==> 第一次删除缓存");

        cacheService.deleteCache(music_id);


        //todo 上传文件,更新数据库字段
        /**
         *
         * **更新数据库**：上传文件,更新数据库字段。
         */

        System.out.println("==> 上传文件,更新数据库字段");

        System.out.println( "参数名称 = " + multipartFile.getName() );
        System.out.println( "文件类型 = " + multipartFile.getContentType() );
        System.out.println( "原文件名 = " + multipartFile.getOriginalFilename() );
        System.out.println( "文件大小 = " + multipartFile.getSize() );

        // 判断 上传的文件类型 是否是音频文件
        if( ! multipartFile.getContentType().startsWith("audio/") ){
            System.out.println( "==> 上传的文件不是音频文件，上传失败" );
            return null;
        }

        // 判断 上传的文件尺寸
        if( multipartFile.getSize() > 20 * 1024 * 1024 ){
            System.out.println( "==> 上传的文件最多20MB，上传失败" );
            return null;
        }

        // 给 上传文件 生成服务器端 文件名
        String filename = UUID.randomUUID().toString() +
                multipartFile.getOriginalFilename().substring( multipartFile.getOriginalFilename().lastIndexOf(".") );


        String filePath = ResourceUtils.getURL("classpath:").getPath() +
                "static/audio/" + filename;
        // 绝对路径前面多了一个/ 去除
        // 确保 filePath 不以斜杠开头
        String fileNewPath = null;
        if (filePath.startsWith("/")) {
            fileNewPath = filePath.substring(1);
        } else if (filePath.startsWith("file:") ) {
            fileNewPath = filePath.replaceFirst("^file:", "");
        } else {
            fileNewPath = filePath;
        }
        System.out.println("fileNewPath: " + fileNewPath);

        // 指定 转移目标文件
        File target = new File(fileNewPath);

        // 将 上传文件 从临时目录 转移到 目标文件夹
//        multipartFile.transferTo( target );

        // 上传的文件保存在 src/main/resources/static/audio/ 目录
        String currentDirectory = System.getProperty("user.dir");  // 当前工作目录是指程序启动时所在的目录
        System.out.println("currentDirectory: " + currentDirectory);
        String targetLocalDirectory = currentDirectory + "/src/main/resources/static/audio/" + filename;
        System.out.println("targetLocalDirectory: " + targetLocalDirectory);
        multipartFile.transferTo(new File(targetLocalDirectory));


        updateMusic(music_id,filename);


        // todo  上传文件,更新数据库字段完成，发送一条消息到消息队列请求再次删除缓存
        /**
         *
         * **发送消息到消息队列**：上传文件,更新数据库字段完成，发送一条消息到消息队列请求再次删除缓存。
         *
         * 消息队列消费者读取到消息，延迟一段时间 (延迟时间设置为，数据库主从同步的时间，再加几百毫秒) ,  消息队列的消费者再次删除缓存。
         *
         */

        System.out.println("==> 上传文件,更新数据库字段完成，发送一条消息到消息队列请求再次删除缓存");

        kafkaTemplate.send( "uploadAudioFileCacheDelete-topic" , JSON.toJSONString( music_id ) , null );

        return "File upload successful: " + fileNewPath +", " + targetLocalDirectory;
    }

}




