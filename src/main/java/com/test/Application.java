package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;

import static com.test.task.DbDocCreat.createDbDoc;

/**
 *
 * Spring 根据 @ComponentScan 注解扫描指定包下的类，寻找任何被 @Component、@Service、@Repository 或 @Controller 标记的类。
 *
 * 对于找到的每个类，Spring 容器通过反射实例化对象，并将这些对象注册到应用上下文中作为 Bean。
 *
 * 这些派生注解有助于提高代码的可读性和语义清晰度，尽管在功能上它们和 @Component 是一样的。
 *
 */
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

        String currentDirectory = System.getProperty("user.dir");
        createDbDoc(currentDirectory + "/src/main/java/com/test/doc/sql");

        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        CentralProcessor cpu = si.getHardware().getProcessor();
        GlobalMemory memory = si.getHardware().getMemory();

        log.info("Operating System: " + os.toString());
        log.info("Processor: " + cpu.toString());
        log.info("Total Memory: " + memory.getTotal());
        log.info("Available Memory: " + memory.getAvailable());

    }
}
