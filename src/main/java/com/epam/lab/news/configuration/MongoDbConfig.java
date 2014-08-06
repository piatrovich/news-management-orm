package com.epam.lab.news.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Defines MongoDB configuration
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0-alpha
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.epam.lab.news.database.data.repo")
@PropertySource("classpath:pool/mongo.properties")
public class MongoDbConfig extends AbstractMongoConfiguration {
    /** Bean for initializing fields using @Value */
    private @Autowired PropertySourcesPlaceholderConfigurer configurer;

    private @Value("${mongo.database}") String database;
    private @Value("${mongo.host}") String host;
    private @Value("${mongo.port}") Integer port;
    private @Value("${mongo.connections.per.host}") Integer connectionsPerHost;
    private @Value("${mongo.threads.multiplier}") Integer threadsMultiplier;
    private @Value("${mongo.connect.timeout}") Integer connectTimeout;
    private @Value("${mongo.max.wait.time}") Integer maxWaitTime;
    private @Value("${mongo.socket.keep.alive}") Boolean socketKeepAlive;
    private @Value("${mongo.socket.timeout}") Integer socketTimeout;

    /**
     * Returns database name.
     * Override if You are using own database.
     *
     * @return Database name
     */
    @Override
    protected String getDatabaseName() {
        return database;
    }

    /**
     * This method return main object for Spring Data layer.
     *
     * @return MongoTemplate object
     * @throws Exception if building object failed
     */
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    /**
     * Return factory for creating database instance from Mongo object.
     *
     * @return SimpleMongoDbFactory object
     * @throws Exception
     */
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongo(), getDatabaseName());
    }

    /**
     * Returns Mongo object initialized by custom pool options.
     *
     * @return Mongo object
     * @throws Exception if pool initialization failed
     */
    @Override
    public Mongo mongo() throws Exception {
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(connectionsPerHost)
                .threadsAllowedToBlockForConnectionMultiplier(threadsMultiplier)
                .connectTimeout(connectTimeout)
                .maxWaitTime(maxWaitTime)
                .socketKeepAlive(socketKeepAlive)
                .socketTimeout(socketTimeout)
                .build();
        return new MongoClient(new ServerAddress(host, port), options);
    }

}
