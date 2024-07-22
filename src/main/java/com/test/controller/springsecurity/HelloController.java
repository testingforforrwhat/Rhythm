package com.test.controller.springsecurity;

import com.test.bean.bo.AdminLoginBo;
import com.test.bean.bo.AdminRegistBo;
import com.test.bean.bo.UsersLoginBo;
import com.test.bean.po.Admin;
import com.test.exception.ResultData;
import com.test.service.AdminService;
import com.test.service.UsersService;
import com.test.utils.JwtTokenUtil;
import com.test.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


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
@Api(tags = "springSecurity模块")
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminService adminService;

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/hello")
    @ResponseBody  // Ensures this method returns plain text
    public String hello() {
        return "hello"; // 返回hello字符串   不返回视图
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // 返回视图名为 login 的模板
    }




    @PostMapping("/springSecurity/register")
    @ResponseBody
    public ResultData register(@RequestBody AdminRegistBo adminRegistBo) {
        // 调用业务逻辑层 的 客户注册功能
        int result = adminService.regist( adminRegistBo );
        System.out.println("result: " + result);

        if( result == 1 ){
            return ResultData.success( "注册成功" );
        }else if( result == -1 ){
            return ResultData.fail( 500,"短信验证码校验失败" );
        }else if( result == -2 ){
            return ResultData.fail( 500,"该手机号码已注册" );
        }else {
            return null;
        }

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
    public ResultData loginBySpringSecurity(@RequestBody AdminLoginBo adminLoginBo) {

        String username = adminLoginBo.getAdminName();
        String password = adminLoginBo.getAdminPass();

        System.out.println("username: " + username);
        System.out.println("password: " + password);

        /**
         * 这里，我们创建了一个 UsernamePasswordAuthenticationToken 对象，
         * 它包含了用户提交的用户名和密码。这是 Spring Security 中用于表示认证请求的标准对象。
         *
         */
        System.out.println("正在创建Spring Security 中用于表示认证请求的标准对象 authRequest,它包含了用户提交的用户名和密码...");
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println("authRequest: " + authRequest);

        /**
         * 此处调用 authenticationManager 的 authenticate 方法来处理认证逻辑。
         * 它会调用配置的 UserDetailsService 和 PasswordEncoder 来验证用户的身份信息。
         *
         * 认证成功后，它会返回一个 Authentication 对象，包含了已认证用户的详细信息和权限。
         */
        System.out.println("authenticationManager开始调用配置的 UserDetailsService 和 PasswordEncoder...,  " +
                "认证成功后，它会返回一个 Authentication 对象，包含了已认证用户的详细信息和权限");

        Authentication authentication = authenticationManager.authenticate(authRequest);
        System.out.println("认证成功，返回一个对象 authentication，包含了已认证(authentication)用户的详细信息和权限(authorization)");
        System.out.println("authentication: \n" + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);  // 保存认证对象，这样可以确保后续请求处理中可以获取到当前已认证用户的信息


        Authentication authentication_ = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication_  (SecurityContextHolder.getContext().getAuthentication(authentication)): \n" + SecurityContextHolder.getContext().getAuthentication());
        System.out.println("authentication_.getDetails(): \n" + authentication_.getDetails());
        if (authentication_ != null && authentication_.getDetails() instanceof WebAuthenticationDetails) {
            WebAuthenticationDetails details = (WebAuthenticationDetails) authentication_.getDetails();

            // 获取 IP 地址
            String remoteAddress = details.getRemoteAddress();

            // 获取会话 ID
            String sessionId = details.getSessionId();

            // 打印 IP 地址和会话 ID
            System.out.println("Remote IP Address: " + remoteAddress);
            System.out.println("Session ID: " + sessionId);
        }


        // 调用业务逻辑层的 客户登录功能
        String token = JwtTokenUtil.generateToken(username);

        // 将签发的令牌，存入Redis中。拼接上Authorization的策略（Bearer Token）前缀。
        redisUtil.set( "Bearer " + token , username , 60 * 24 );

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

    @GetMapping( value = {"/","/index"} )
    @ResponseBody
    @ApiOperation("返回 当前登录用户 登录认证authentication信息")
    public ResultData index( Authentication authentication ) {

        System.out.println( "Authentication = " + authentication);
//        Admin admin = (Admin) authentication.getPrincipal();
//        System.out.println( "admin = " + admin);

        return ResultData.success(authentication);
    }


    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home Page");
        model.addAttribute("message", "Welcome to the secured page!");
        return "home";
    }

}