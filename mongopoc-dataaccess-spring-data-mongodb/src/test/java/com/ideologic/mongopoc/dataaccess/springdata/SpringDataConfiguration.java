package com.ideologic.mongopoc.dataaccess.springdata;

import com.ideologic.mongopoc.configuration.MongopocConfiguration;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author tewe
 */
@Configuration
public class SpringDataConfiguration {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(MongopocConfiguration.MONGOPOC_HOST, MongopocConfiguration.MONGOPOC_PORT), MongopocConfiguration.MONGOPOC_DATABASE_NAME);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

}
