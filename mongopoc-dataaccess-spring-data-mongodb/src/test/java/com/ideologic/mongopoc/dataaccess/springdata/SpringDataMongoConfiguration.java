package com.ideologic.mongopoc.dataaccess.springdata;

import com.ideologic.mongopoc.configuration.MongopocConfiguration;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * @author tewe
 */
@Configuration
public class SpringDataMongoConfiguration extends AbstractMongoConfiguration {


    @Override
    public String getDatabaseName() {
        return MongopocConfiguration.MONGOPOC_DATABASE_NAME;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(MongopocConfiguration.MONGOPOC_HOST, MongopocConfiguration.MONGOPOC_PORT);
    }

}
