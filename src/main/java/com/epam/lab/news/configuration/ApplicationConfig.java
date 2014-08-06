package com.epam.lab.news.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.epam.lab.news"})
@Configuration
@Import({ApplicationBeans.class})
public class ApplicationConfig extends WebMvcConfigurerAdapter {
    @Autowired
    ApplicationBeans beans;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(3600);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(1);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(beans.localeChangeInterceptor());
    }

}
