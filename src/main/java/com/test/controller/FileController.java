package com.test.controller;

import com.test.service.MusicService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import static com.test.task.DbDocCreat.createDbDoc;

@RestController
@RequestMapping("/api")
public class FileController {


    @Resource
    private MusicService musicService;


    @PostMapping("/file/upload")
    public Object upload( MultipartFile multipartFile ) throws IOException {

        System.out.println( "参数名称 = " + multipartFile.getName() );
        System.out.println( "文件类型 = " + multipartFile.getContentType() );
        System.out.println( "原文件名 = " + multipartFile.getOriginalFilename() );
        System.out.println( "文件大小 = " + multipartFile.getSize() );

        // 判断 上传的文件类型 是否是图片
        if( ! multipartFile.getContentType().startsWith("image/") ){
            System.out.println( "==> 上传的文件不是图片，上传失败" );
            return null;
        }

        // 判断 上传的文件尺寸
        if( multipartFile.getSize() > 2 * 1024 * 1024 ){
            System.out.println( "==> 上传的文件最多2MB，上传失败" );
            return null;
        }

        // 给 上传文件 生成服务器端 文件名
        String filename = UUID.randomUUID().toString() +
                multipartFile.getOriginalFilename().substring( multipartFile.getOriginalFilename().lastIndexOf(".") );


        String filePath = ResourceUtils.getURL("classpath:").getPath() +
                "static/uploads/" + filename;
        // 绝对路径前面多了一个/ 去除
        String fileNewPath = filePath.substring(1);
        System.out.println("fileNewPath: " + fileNewPath);

        // 指定 转移目标文件
        File target = new File( filePath );

        // 将 上传文件 从临时目录 转移到 目标文件夹
        multipartFile.transferTo( target );

        return null;
    }

    @PostMapping("/file/upload/audioFile")
    public Object uploadAudioFile( MultipartFile multipartFile ) throws IOException {

        return musicService.uploadAudioFile(multipartFile);

    }

    /**
     *
     * 'Required request part 'multipartFile' is not present'
     *
     * value值即选择的文件重新选择. 问题消失。  问题在于我之前并未变更该文件选择, 且一开始是可以upload, 突然出现问题。
     *
     * https://blog.csdn.net/yigeshaoziwei/article/details/136688851
     * https://blog.csdn.net/u013231332/article/details/105624361
     * https://cloud.tencent.com/developer/article/1880260
     * https://juejin.cn/post/7168369943421911047
     * https://www.cnblogs.com/tobycold/p/17867856.html
     *
     * @param multipartFile
     * @param music_id
     * @return
     * @throws IOException
     */
    @PostMapping("/file/upload/audioFile/{music_id}")
    @ResponseBody
    public Object uploadAudioFileByMusicId( @RequestParam("multipartFile") MultipartFile multipartFile, @PathVariable("music_id") Integer music_id ) throws IOException, NoSuchMethodException {

        return musicService.uploadAudioFileByMusicId(multipartFile,music_id);

    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download( String filename ) throws IOException {

        // 指定要下载的文件
        File file = new File(
                ResourceUtils.getURL("classpath:").getPath() +
                        "static/downloads/" + filename
        );

        // 实例化 响应报文头 对象
        HttpHeaders headers = new HttpHeaders();
        // 设置 浏览器下载文件时的文件名
        headers.setContentDispositionFormData( "attachment" , filename );
        // 设置 浏览器解析响应报文的方式 以 输出流 的方式解析
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // 返回响应报文
        return new ResponseEntity<byte[]>(
                FileUtils.readFileToByteArray( file ),  // 响应报文体
                headers,                                // 响应报文头
                HttpStatus.CREATED                      // 响应状态
        );

    }

}
