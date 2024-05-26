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


/**
 *
 *
 * List<HttpMessageConverter<?>> converters 是一个用于存储 HTTP 消息转换器的列表。在 Spring MVC 中，HTTP 消息转换器被用来将 Java 对象 (接口返回的是一个对象/String/List/Map，但是SpringMVC会将它转成JSON，但是前提是引入json依赖并开启了mvc的注解驱动;
 *                                                                                                                                                              springboot 默认添加jackson-databind作为json处理器，此时不需要添加额外的json处理器就能自动以之返回一段json) 转换为 HTTP 请求或响应的内容，如 JSON、XML 等，并且负责处理请求和响应之间的格式转换。
 *
 * 在您的 Spring MVC 应用程序中，您可以通过配置 converters 列表来添加或配置不同类型的 HTTP 消息转换器，以满足您的需求。例如，您可以添加一个用于处理 JSON 数据的转换器、一个用于处理 XML 数据的转换器等。
 *
 * 在配置 converters 列表时，您需要将适当类型的消息转换器添加到列表中，以确保 Spring MVC 能够正确地序列化和反序列化数据。
 *
 *
 * AbstractGenericHttpMessageConverter
 * AbstractJackson2HttpMessageConverter
 * AbstractJaxb2HttpMessageConverter
 * AbstractJsonHttpMessageConverter
 * AbstractWireFeedHttpMessageConverter
 * AbstractXmlHttpMessageConverter
 * AtomFeedHttpMessageConverter
 * ByteArrayHttpMessageConverter
 * FastJsonHttpMessageConverter
 * FastJsonHttpMessageConverter4
 * FastJsonpHttpMessageConverter4
 * GsonHttpMessageConverter
 * Jaxb2CollectionHttpMessageConverter
 * Jaxb2RootElementHttpMessageConverter
 * JsonbHttpMessageConverter
 * MappingJackson2CborHttpMessageConverter
 * MappingJackson2HttpMessageConverter
 * MappingJackson2SmileHttpMessageConverter
 * MappingJackson2XmlHttpMessageConverter
 * MarshallingHttpMessageConverter
 * ObjectToStringHttpMessageConverter
 * ProjectingJackson2HttpMessageConverter
 * ProtobufHttpMessageConverter
 * ProtobufJsonFormatHttpMessageConverter
 * ResourceHttpMessageConverter
 * ResourceRegionHttpMessageConverter
 * RssChannelHttpMessageConverter
 * SourceHttpMessageConverter
 * StringHttpMessageConverter
 * XmlBeamHttpMessageConverter
 *
 */

