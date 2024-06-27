package com.test.utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class AudioParserUtils {


    @Resource
    private RedisUtil redisUtil;


    public byte[] parseAudio(String filePath) {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filePath);
        try {
            grabber.start();
            Frame frame;
            Java2DFrameConverter converter = new Java2DFrameConverter();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            while ((frame = grabber.grab()) != null) {
                BufferedImage image = converter.getBufferedImage(frame);
                ImageIO.write(image, "jpg", outputStream);
            }

            grabber.stop();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


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

    public void incrementPlayCount(String music_id) {
        redisUtil.incr("audio:playcountByWeekByMusicId:" + music_id, 1);
    }

}