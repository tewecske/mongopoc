import com.ideologic.mongopoc.domain.customer.Customer;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author: tewe
 */
@Test
@ContextConfiguration(classes=SpringDataConfiguration.class)
public class SpringDataMongoDbCustomerDaoTest extends AbstractTestNGSpringContextTests {

    private MongoOperations mongoOperation;

    @BeforeClass
    public void beforeClass() throws Exception {
        mongoOperation = (MongoOperations)applicationContext.getBean("mongoTemplate");
        mongoOperation.dropCollection("customers");
    }

    @Test
    public void testInsert() throws Exception {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john@doe.com");

        mongoOperation.save(customer, "customers");

        Customer actual = mongoOperation.findOne(new Query(
                Criteria.where("name").is("John Doe")
        ), Customer.class,"customers");

        assertEquals(actual.getName(), customer.getName());
        assertEquals(actual.getEmail(), customer.getEmail());


    }
}
