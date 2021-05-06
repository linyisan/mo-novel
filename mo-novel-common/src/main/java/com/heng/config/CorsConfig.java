package com.heng.config;

import com.heng.converter.DateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域处理,格式转换
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**") //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
                .allowedOriginPatterns("*") //开放哪些ip、端口、域名的访问权限
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS") //开放哪些Http方法，允许跨域访问
                .allowCredentials(true) //是否允许发送Cookie信息
                .maxAge(3600)
                .allowedHeaders("*"); //允许HTTP请求中的携带哪些Header信息
    }

    @Override
    public void addFormatters(FormatterRegistry registry)
    {
        registry.addConverter(new DateConverter());
    }
}
