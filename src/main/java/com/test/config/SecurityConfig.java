package com.test.config;

import com.test.security.*;
import com.test.service.AdminService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AdminService adminService;

    @Resource
    private PermissionAuthority permissionAuthority;

    @Resource
    private PermissionValid permissionValid;

    @Resource
    private LoginErrorHandle loginErrorHandle;

    @Resource
    private LoginSuccessHandle loginSuccessHandle;

    @Resource
    private LogoutSuccessHandle logoutSuccessHandle;

    @Resource
    private PermissionErrorHandle permissionErrorHandle;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 配置登录验证方式
        // Spring Security 可以同时使用基于内存的用户存储和基于数据库的用户存储
        auth
            .userDetailsService(adminService)  // 基于数据库的认证
            .passwordEncoder(new BCryptPasswordEncoder())

            .and()  // 基于内存的认证
            .inMemoryAuthentication()

            .withUser("user")
            .password(new BCryptPasswordEncoder().encode("password"))
            .roles("USER")

            .and()
            .withUser("admin")
            .password(new BCryptPasswordEncoder().encode("admin_password"))
            .roles("ADMIN");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置例外路径
        /**
         *
         * 在Spring Security中，web.ignoring().antMatchers() 方法用于配置Spring Security完全忽略的请求路径。
         * 被忽略的路径将不会经过Spring Security的过滤器链。
         * 这对于静态资源、API文档等路径非常有用，因为这些路径通常不需要身份验证。
         *
         */
        web.ignoring().antMatchers("/login",
                        "/index/login",
                        "/common/**","/img/**",
                        "/css/**",
                        "/js/**",
                        "/favicon.ico")
                .antMatchers("/swagger-ui.html",
                        "/swagger-resources/**",
                        "/images/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/configuration/security",
                        "/api/music",
                        "/api/music/**",
                        "/api/playAudio/**");
    }


    /**
     *
     * @EnableWebSecurity  URL级别
     * @EnableGlobalMethodSecurity(prePostEnabled = true)  启用方法级别的安全性
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置Security
        http.headers().frameOptions().disable() // 允许iframe嵌套

                .and()
          		.authorizeRequests()  // spring security    自动读取url            开启权限认证
                .antMatchers("/hello").authenticated() // 需要认证的路径
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(permissionAuthority);
                        o.setAccessDecisionManager(permissionValid);
                        return o;
                    }
                })

                .and()  // 定义登录页面
                .formLogin()  // 开启表单登陆验证  http://127.0.0.1:8001/hello
                .loginPage("/login")  // 登陆表单页面的url路径  post             // spring security            使用框架的登录页
                .loginProcessingUrl("/login")  // 登陆表单处理方法的url路径  get
                .usernameParameter("admin_name")
                .passwordParameter("admin_pass")
                .failureHandler( loginErrorHandle )
                .successHandler( loginSuccessHandle )
                .permitAll()

                .and()
                .logout()
                .logoutUrl("/index/logout")
                .logoutSuccessHandler( logoutSuccessHandle )
                .permitAll()

                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(permissionErrorHandle);
    }
}