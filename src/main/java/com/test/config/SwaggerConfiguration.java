package com.test.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Component
@EnableOpenApi
@Data
public class SwaggerConfiguration {


    @Bean
    public Docket advertisementsDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("广告模块")
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    @Bean
    public Docket musicCategoriesDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("音乐管理模块")
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    @Bean
    public Docket musicCDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("音乐模块")
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    @Bean
    public Docket favoritesCDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("收藏和分享模块")
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    @Bean
    public Docket UsersCDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("用户管理模块")
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test"))
                .paths(PathSelectors.ant("/users/**"))
                .build();
    }

    @Bean
    public Docket SpringSecurityDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("springSecurity模块")
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    /*api信息*/
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                // 头部信息
                .title("Rhythm")
                // 描述
                .description("rhythm接口文档")
                // 作者，网站和邮箱
                .contact(new Contact("test","http://www.rythm.com","123456789@qq.com"))
                // 版本号
                .version("V1.0")
                .build();

    }
}