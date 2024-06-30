package com.test.controller.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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