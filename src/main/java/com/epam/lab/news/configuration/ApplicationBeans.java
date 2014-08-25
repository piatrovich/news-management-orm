package com.epam.lab.news.configuration;

import com.epam.lab.news.validation.ArticleValidator;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

/**
 * Contains configured application beans.
 */
@Configuration
@PropertySources({@PropertySource("classpath:application.properties"),
                    @PropertySource("classpath:oracle.properties")})
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

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.user}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public OracleDataSource oracleDataSource() throws SQLException {
        Locale.setDefault(Locale.ENGLISH);
        OracleConnectionPoolDataSource dataSource = new OracleConnectionPoolDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(oracleDataSource());
        factoryBean.setPackagesToScan("com.epam.lab.news.bean");
        factoryBean.setHibernateProperties(sessionFactoryProperties());
        /*new LocalSessionFactoryBuilder(oracleDataSource())
                .addPackages("com.oracle.driver.bean")
                .buildSessionFactory();*/
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws SQLException{
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactory().getObject());
        return manager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor postProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    Properties sessionFactoryProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }


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
