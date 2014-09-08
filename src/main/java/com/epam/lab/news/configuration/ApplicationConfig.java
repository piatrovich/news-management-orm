package com.epam.lab.news.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class configures application
 */
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.epam.lab.news"})
@Configuration
@Import({ApplicationBeans.class})
public class ApplicationConfig extends WebMvcConfigurerAdapter {
    @Autowired
    ApplicationBeans beans;

    /**
     * Adding resource handlers to handler registry
     *
     * @param registry ResourceHandlerRegistry object
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(3600);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(3600);
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/").setCachePeriod(3600);
    }

    /**
     * Adding interceptor to registry for intercepting locale changing
     *
     * @param registry InterceptorRegistry object
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(beans.localeChangeInterceptor());
    }

}
