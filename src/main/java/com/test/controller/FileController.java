package com.test.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import static com.test.task.DbDocCreat.createDbDoc;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
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

    @PostMapping("/upload/audioFile")
    public Object uploadAudioFile( MultipartFile multipartFile ) throws IOException {

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
        String fileNewPath = filePath.substring(1);
        System.out.println("fileNewPath: " + fileNewPath);

        // 指定 转移目标文件
        File target = new File(fileNewPath);

        // 将 上传文件 从临时目录 转移到 目标文件夹
//        multipartFile.transferTo( target );

        // 上传的文件保存在 src/main/resources/static/audio/ 目录
        String currentDirectory = System.getProperty("user.dir");  // 当前工作目录是指程序启动时所在的目录
        System.out.println("currentDirectory: " + currentDirectory);
        String targetLocalDirectory = currentDirectory + "/src/main/resources/static/audio/" + filename;
        multipartFile.transferTo(new File(targetLocalDirectory));

        return "File upload successful: " + fileNewPath +", " + targetLocalDirectory;
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
