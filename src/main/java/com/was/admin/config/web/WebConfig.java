package com.was.admin.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**", "/img/**", "/js/**", "/fonts/**", "/assets/**", "/favicon.ico", "/.well-known/pki-validation/**")
                .addResourceLocations("classpath:/static/css/", "classpath:/static/img/", "classpath:/static/js/", "classpath:/static/fonts/", "classpath:/static/favicon.ico", "classpath:/static/assets/", "classpath:/static/.well-known/pki-validation/")
                .setCachePeriod(20)
        ;
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/error").setViewName("index");
        WebMvcConfigurer.super.addViewControllers(registry);
    }


}
