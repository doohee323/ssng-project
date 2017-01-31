package com.mycompany.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Import({SecurityConfig.class})
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { "com.mycompany.myproject.web.controller" })
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
        return new ResourceUrlEncodingFilter();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/vendor/**")
                .addResourceLocations("/resources/vendor/")
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new GzipResourceResolver())
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }
    
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//      registry.addMapping("/**")
//      .allowedOrigins("http://localhost", "http://localhost:8000")
//      .allowedMethods("OPTIONS", "HEAD", "GET", "POST", "PUT")
//      .allowedHeaders("application/json;charset=utf-8", "text/json;charset=utf-8")
//      .exposedHeaders("header1", "header2")
//      .allowCredentials(false).maxAge(3600);
//      registry.addMapping("/resources/**")
//      .allowedOrigins("http://localhost", "http://localhost:8000");
//    }    
}
