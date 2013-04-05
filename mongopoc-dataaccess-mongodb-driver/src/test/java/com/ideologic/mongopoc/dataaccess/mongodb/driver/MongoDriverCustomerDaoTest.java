package com.ideologic.mongopoc.dataaccess.mongodb.driver;

import com.ideologic.mongopoc.domain.customer.Customer;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.UnknownHostException;

import static org.testng.Assert.assertEquals;

/**
 * @author tewe
 */
@Test
public class MongoDriverCustomerDaoTest {

    private DBCollection customerCollection;
    private DB db;
    private MongoDriverCustomerDao mongoDriverCustomerDao;

    @BeforeClass
    public void beforeClass() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost", 27770);
        db = mongoClient.getDB("mongopoc");
        customerCollection = db.getCollection("customer");
        mongoDriverCustomerDao = new MongoDriverCustomerDao();
        mongoDriverCustomerDao.setCustomerCollection(customerCollection);
        customerCollection.drop();
    }

    @Test
    public void testInsert() throws Exception {

        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john@doe.com");

        mongoDriverCustomerDao.create(customer);

        DBObject actual = customerCollection.findOne(
                BasicDBObjectBuilder.start("name", "John Doe").append("email", "john@doe.com").get());
        assertEquals(actual.get("name"), customer.getName());
        assertEquals(actual.get("email"), customer.getEmail());

        DBObject dbObject = customerCollection.findOne((DBObject) JSON.parse("{'name':'John Doe', 'email':'john@doe.com'}"));
        assertEquals(dbObject.get("name"), customer.getName());
        assertEquals(dbObject.get("email"), customer.getEmail());



    }
}
