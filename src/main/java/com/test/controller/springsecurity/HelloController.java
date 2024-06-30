package com.test.controller.springsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * 只返回了 login 字符串
 *
 * 可能的原因有：
 *
 * Thymeleaf 模板文件未放置在正确的目录中：默认情况下，Thymeleaf 模板应该放在 src/main/resources/templates/ 目录中。
 * 视图解析器未正确配置：Spring Boot 默认会自动配置视图解析器，但你可能需要检查配置以确保其正确。
 * RESTful API：如果你创建的是 RESTful API 项目，可能会出现与视图解析相关的问题，因为 Spring Boot 中的 @RestController 默认返回的是 JSON 或其他序列化数据，而不是视图。
 * 确保你没有在控制器类上使用 @RestController，而应该使用 @Controller 注解。
 *
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello"; // 返回视图名为 hello 的模板
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // 返回视图名为 login 的模板
    }
}