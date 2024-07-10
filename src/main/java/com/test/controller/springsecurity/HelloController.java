package com.test.controller.springsecurity;

import com.test.bean.bo.AdminLoginBo;
import com.test.bean.bo.UsersLoginBo;
import com.test.exception.ResultData;
import com.test.service.AdminService;
import com.test.service.UsersService;
import com.test.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminService adminService;

    @GetMapping("/hello")
    @ResponseBody  // Ensures this method returns plain text
    public String hello() {
        return "hello"; // 返回hello字符串   不返回视图
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // 返回视图名为 login 的模板
    }

    /**
     *
     * 实现一个控制器来处理前端的登录请求，认证成功后生成并返回 JWT 令牌
     *
     * @param adminLoginBo
     * @return
     */
    @PostMapping("/springSecurity/login")
    @ResponseBody
    public ResultData loginBySpringSecurity(AdminLoginBo adminLoginBo) {

        String username = adminLoginBo.getAdminName();
        String password = adminLoginBo.getAdminPass();

        System.out.println("username: " + username);
        System.out.println("password: " + password);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println("authRequest: " + authRequest);
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 调用业务逻辑层的 客户登录功能
        String token = JwtTokenUtil.generateToken(username);
        // 实例化 DTO 对象，封装响应报文体
//        DTO dto = new DTO();
        // 判断 登录是否成功
        if( token == null ){
            // 登录失败
//            dto.setCode( 401 );
//            dto.setMessage( "手机号或密码错误，请重新填写" );
            return ResultData.fail(500,"请登录");
        }else{
            // 登录成功
//            dto.setCode( 200 );
//            dto.setMessage( "登录成功" );
//            dto.setData( token );
            return ResultData.success(token);
        }

    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home Page");
        model.addAttribute("message", "Welcome to the secured page!");
        return "home";
    }

}