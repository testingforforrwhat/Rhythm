package com.test.config;

import com.test.security.*;
import com.test.service.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.Arrays;


/**
 *
 * 通过同时使用@EnableWebSecurity和@EnableGlobalMethodSecurity(prePostEnabled = true)，你可以确保整个应用程序的Web安全性和方法级别的安全性得到全面的控制。
 *
 */
@Configuration
@EnableWebSecurity
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
            .passwordEncoder(passwordEncoder());

//            .and()  // 基于内存的认证
//            .inMemoryAuthentication()
//
//            .withUser("user")
//            .password(passwordEncoder().encode("user"))
//            .roles("USER")
//
//            .and()
//            .withUser("admin")
//            .password(passwordEncoder().encode("admin_password"))
//            .roles("ADMIN");

    }

    /**
     * 确保所有的密码编码和解码使用同一个 BCryptPasswordEncoder 实例
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
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
                        "/api/playAudio/**",
                        "/swagger-ui.html",
                        "/swagger-ui.html",
                        "/api/users/login",
                        "/api/users/regist",
                        "/sms/validate/**");
    }


    /**
     *
     * @EnableWebSecurity  URL级别
     * @EnableGlobalMethodSecurity(prePostEnabled = true)  启用方法级别的安全性，使得可以在方法上使用注解进行基于角色的访问控制
     *
     * @EnableGlobalMethodSecurity(prePostEnabled = true)注解允许使用@PreAuthorize和@PostAuthorize等注解来控制方法级别的访问。
     *
     * @GetMapping("/hello")
     *     @PreAuthorize("hasRole('USER')")  // 仅允许具有USER角色的用户访问
     *     public @ResponseBody String hello() {
     *         return "Hello, User!";
     *     }
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
                .antMatchers("/login", "/error/**", "/css/**", "/login.html","/actuator/**").permitAll() // 这些路径不需要认证
                //.antMatchers("/actuator/**").hasRole("user")
                .antMatchers("/hello").authenticated() // 需要认证的路径
                .anyRequest().authenticated() // 别的所有请求都需要认证
                //.and()
                //.httpBasic()  // 使用 HTTP Basic 认证

                /**
                 *
                 * 使用自定义的安全元数据源（SecurityMetadataSource）和访问决策管理器（AccessDecisionManager）注入Spring Security过滤器链
                 * ObjectPostProcessor定制FilterSecurityInterceptor
                 *
                 * FilterSecurityInterceptor可以实现自定义的权限控制机制
                 *
                 */
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        o.setSecurityMetadataSource(permissionAuthority);  // 自定义SecurityMetadataSource实现复杂的安全需求(即基于数据库)
//                        o.setAccessDecisionManager(permissionValid);  // 自定义AccessDecisionManager实现复杂的安全需求(即基于数据库)
//                        return o;
//                    }
//                })
//                .anyRequest().authenticated()  // 所有请求都需要经过认证

                .and()  // 定义登录页面
                .formLogin()  // 开启表单登陆验证  http://127.0.0.1:8001/hello
                .loginPage("/login")  // 登陆表单页面的url路径  get             // spring security            使用框架的登录页
                .loginProcessingUrl("/perform_login")  // 登陆表单处理方法的url路径  post    确保 loginProcessingUrl("/perform_login") 与表单的 action="@{/perform_login}" 一致
                .usernameParameter("username")
                .passwordParameter("password")
                .failureHandler( loginErrorHandle )
                .successHandler( loginSuccessHandle )
                .permitAll()  // 允许所有用户访问登录页

                /**
                 *
                 * 在大多数应用程序中，用户需要通过点击“登出”按钮或链接来触发登出请求。这个按钮或链接通常会指向 Spring Security 配置的 /logout URL。
                 *
                 * 为了实现这一功能，通常在前端页面中添加一个登出按钮或链接，并将其指向 /logout。
                 * 当用户点击这个按钮或链接时，会向服务器发送 /logout 请求，Spring Security 会自动处理这个请求并执行登出
                 *
                 */
                .and()
                .logout()
                .logoutUrl("/logout")  // 访问 /logout 进行登出，Spring Security 会自动处理登出请求
                .logoutSuccessHandler( logoutSuccessHandle )
                .permitAll()  // 允许所有用户访问注销页

                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(permissionErrorHandle)

                .and()
                .cors();  // Enable CORS support

    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost:8002"));  // Replace with your frontend URL
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}