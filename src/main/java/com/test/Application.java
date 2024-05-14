package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableScheduling
@EnableWebMvc
@SpringBootApplication
@MapperScan("com.test.mapper")
public class Application {

    public static void main(String[] args) {


        // 创建一个Logger对象
        // 只有准确填写.getLogger()参数, 才能准确获取对应类的日志信息
        Logger log = LoggerFactory.getLogger(Application.class);

        SpringApplication.run(Application.class,args);

        log.trace("这是跟踪级别的信息");  // 最详细
        log.debug("这是调试信息");
        log.info("这是默认级别的信息");  // logback默认 (故.log.console只记录 展示 .info.warn.error)
        log.warn("这是警告信息");
        log.error("这是错误信息");

    }
}
