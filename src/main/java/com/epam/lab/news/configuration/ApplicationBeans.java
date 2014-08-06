package com.epam.lab.news.configuration;

import com.epam.lab.news.aspect.ServiceExceptionInterceptor;
import com.epam.lab.news.aspect.ApplicationAPILogger;
import com.epam.lab.news.aspect.ConnectionPoolObserver;
import com.epam.lab.news.validation.ArticleValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

/**
 * Contains configured application beans.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationBeans {
    @Value("${resolver.prefix}")
    private String resolverPrefix;

    @Value("${resolver.suffix}")
    private String resolverSuffix;

    @Value("${default.locale}")
    private String defaultLocale;

    @Value("${interceptor.name}")
    private String interceptorName;

    @Value("${cache.time}")
    private Integer cacheTime;

    /**
     * Helps to controller in search required views
     *
     * @return Configured .jsp views resolver
     */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(resolverPrefix);
        resolver.setSuffix(resolverSuffix);
        return resolver;
    }

    /**
     * Returns configured resolver. Default language: EN
     *
     * @return Resolver for locales
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale(defaultLocale));
        return localeResolver;
    }

    /**
     * Returns configured by defaults locale interceptor
     *
     * @return Interceptor for changing locale
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
        changeInterceptor.setParamName(interceptorName);
        return changeInterceptor;
    }

    /**
     * This bean helps to localize application views
     *
     * @return Configured message source
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(cacheTime);
        messageSource.setBasename("classpath:interface");
        return messageSource;
    }

    /**
     * This configurer needs for loading data from properties.
     *
     * @return PropertySourcesPlaceholderConfigurer object
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Logger for watching public application REST API
     *
     * @return Aspect which observing public API
     */
    @Bean
    public ApplicationAPILogger getApplicationAPILogger(){
        return new ApplicationAPILogger();
    }

    /**
     * This bean handles exceptions that can be thrown from repositories
     *
     * @return Exception interceptor
     */
    @Bean
    public ServiceExceptionInterceptor getServiceExceptionInterceptor(){
        return new ServiceExceptionInterceptor();
    }

    /**
     * Observes and writing logs when connections taken from pool and returned in pool
     *
     * @return Observer for connection pool
     */
    @Bean
    public ConnectionPoolObserver getConnectionPoolObserver(){
        return new ConnectionPoolObserver();
    }

    /**
     * Validator for articles
     *
     * @return Validator object
     */
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public ArticleValidator validator(){
        return new ArticleValidator();
    }

}
