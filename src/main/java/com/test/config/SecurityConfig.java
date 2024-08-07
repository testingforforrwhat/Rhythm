package com.test.config;

import com.test.security.*;
import com.test.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 开启注解权限校验，否则无法识别@PreAuthorize注解
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

    @Resource
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 配置登录验证方式
        // Spring Security 可以同时使用基于内存的用户存储和基于数据库的用户存储
        /**
         *
         * 　用户根据实际情况设置这7个方法的返回值。因为默认情况下不需要开发者自己进行密码角色等信息的比对，开发者只需要提供相关信息即可，
         *  例如getPassword()方法返回的密码和用户输入的登录密码不匹配，会自动抛出BadCredentialsException异常，
         *     isAccountNonExpired()方法返回了false，会自动抛出AccountExpiredException异常
         *     getAuthorities()方法用来获取当前用户所具有的角色信息，本案例中，用户所具有的角色存储在roles属性中，因此该方法直接遍历roles属性，然后构造SimpleGrantedAuthority集合并返回
         *
         */
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

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 确保所有的密码编码和解码使用同一个 BCryptPasswordEncoder 实例
     *
     * 最新版本的Spring Security中，NoOpPasswordEncoder的构造方法被设置为了私有，不能直接实例化，
     *
     * NoOpPasswordEncoder 应该通过其工厂方法 getInstance() 获取实例，而不能直接通过new关键字创建实例
     *
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
                        "/sms/validate/**",
                        "/actuator/**",
                        "/springSecurity/register",
                        "/swagger-ui/**",
                        "/v3/api-docs",
                        "/api/springSecurity/register");
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
                .csrf().disable()
                // 自定义异常处理
                .exceptionHandling()
                  // 如果请求中未包含有效的 JWT，JwtRequestFilter 不会设置认证，CustomAuthenticationEntryPoint 将截获并返回 401 状态码
                  .authenticationEntryPoint(customAuthenticationEntryPoint)
                  // 如果请求中包含有效的 JWT，但用户没有所需的 ADMIN 权限，权限检查会失败，CustomAccessDeniedHandler 将截获并返回 403 状态码
                  // 在 Spring Security 配置中，如果你配置了多个 .accessDeniedHandler()，只会使用最后一个配置的 AccessDeniedHandler
                  .accessDeniedHandler(customAccessDeniedHandler)
                .and()
          		.authorizeRequests()  // spring security    自动读取url            开启权限认证
                  .antMatchers("/login",
                          "/error/**",
                          "/css/**",
                          "/login.html",
                          "/api/springSecurity/login").permitAll() // 这些路径不需要认证
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
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(permissionAuthority);  // 自定义SecurityMetadataSource实现复杂的安全需求(即基于数据库)
                        o.setAccessDecisionManager(permissionValid);  // 自定义AccessDecisionManager实现复杂的安全需求(即基于数据库)
                        return o;
                    }
                })
//                .anyRequest().authenticated()  // 所有请求都需要经过认证

                /**
                 * 在前后端分离的架构中，前端和后端是通过API交互的，后端不需要直接处理登录页面。
                 * 因此，Spring Security配置文件中不需要指定登录页面和登录处理URL。
                 * 相反，我们需要提供一个用于身份认证的API端点。
                 * 在后端进行身份验证后，生成一个JWT令牌返回给前端，前端将JWT令牌存储并在每个请求中携带该令牌。
                 */
//                .and()  // 定义登录页面
//                .formLogin()  // 开启表单登陆验证  http://127.0.0.1:8001/hello
//                .loginPage("/login")  // 登陆表单页面的url路径  get             // spring security            使用框架的登录页
//                .loginProcessingUrl("/perform_login")  // 登陆表单处理方法的url路径  post    确保 loginProcessingUrl("/perform_login") 与表单的 action="@{/perform_login}" 一致
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .failureHandler( loginErrorHandle )
//                .successHandler( loginSuccessHandle )
//                .permitAll()  // 允许所有用户访问登录页

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


//                /**
//                 * 添加权限不足异常处理器
//                 */
//                .and()
//                .csrf()
//                .disable()
//                .exceptionHandling()
//                .accessDeniedHandler(permissionErrorHandle)

                .and()
                .cors()  // Enable CORS support

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // 配置无状态会话（通常用于 RESTful API），要求每个请求都携带认证信息（例如，通过 JWT 令牌）

        /**
         * 配置 JWT 过滤器
         */
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
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