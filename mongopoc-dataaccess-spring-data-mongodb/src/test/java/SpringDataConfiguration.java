import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author: tewe
 */
@Configuration
public class SpringDataConfiguration {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new Mongo("localhost", 27770), "mongopoc");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

}

//@Configuration
//public class SpringDataConfiguration extends AbstractMongoConfiguration {
//    @Override
//    public String getDatabaseName() {
//        return "mongopoc";
//    }
//
//    @Override
//    @Bean
//    public Mongo mongo() throws Exception {
//        return new Mongo("localhost", 27770);
//    }
//}
