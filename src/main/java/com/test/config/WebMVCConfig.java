package com.test.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 静态资源访问自定义配置
 *
 * 代码中配置的优先级高于application.properties配置的优先级
 *
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 使用 fastjson 序列化，会导致 @JsonIgnore 失效，可以使用 @JSONField(serialize = false) 替换

        /**
         *
         * 如果json字符串经过两次序列化，会在各个属性前多一个\
         *
         * @RedisCache( duration = 60 * 60 )
         *     @GetMapping(value = "/playAudio/{music_id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
         *     @ResponseBody
         *     public String playAudio(@PathVariable String music_id) throws IOException {
         *
         *     该接口返回的是String, 是json格式的String
         *                        是SpringRest自动生成返回的 json格式的String
         *
         *      如果此处直接 FastJsonMessageConverter, 或者 StringMessageConverter 在之后运行, 则接口返回的 String, 会再次序列化, 这就
         *
         *
         *      参考:
         *
         *      参考类: StringHttpMessageConverter AbstractHttpMessageConverter
         *
         *      https://juejin.cn/post/6945318639071576078?from=search-suggest
         *
         *
         */
        converters.clear();
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(converter);

//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        List<MediaType> supportMediaTypeList = new ArrayList<>();
//        supportMediaTypeList.add(MediaType.APPLICATION_JSON);
//        supportMediaTypeList.add(MediaType.APPLICATION_OCTET_STREAM);
//        FastJsonConfig config = new FastJsonConfig();
//        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        config.setCharset(Charset.forName("UTF-8"));
//        config.setSerializerFeatures(
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.PrettyFormat);
//        fastJsonHttpMessageConverter.setFastJsonConfig(config);
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportMediaTypeList);
//        converters.add(fastJsonHttpMessageConverter);

    }

}