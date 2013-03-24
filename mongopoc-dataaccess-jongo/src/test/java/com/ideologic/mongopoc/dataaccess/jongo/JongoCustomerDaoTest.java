package com.ideologic.mongopoc.dataaccess.jongo;

import com.ideologic.mongopoc.domain.customer.Customer;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.UnknownHostException;

import static org.testng.Assert.assertEquals;

/**
 * @author: tewe
 */
@Test
public class JongoCustomerDaoTest {

    private DB db;
    private MongoCollection customerCollection;
    private JongoCustomerDao jongoCustomerDao;

    @BeforeClass
    public void beforeClass() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost", 27770);
        db = mongoClient.getDB("mongopoc");
        Jongo jongo = new Jongo(db);
        customerCollection = jongo.getCollection("customer");
        jongoCustomerDao = new JongoCustomerDao();
        jongoCustomerDao.setCustomerCollection(customerCollection);
        customerCollection.drop();
    }


    @Test
    public void testCreate() {
        Customer customer = new Customer();
        customer.setName("Jon Go");
        customer.setEmail("jon@go.com");

        jongoCustomerDao.create(customer);

        Customer actual = customerCollection.findOne("{'name':#}", "Jon Go").as(Customer.class);

        assertEquals(actual.getName(), customer.getName());
        assertEquals(actual.getEmail(), customer.getEmail());
    }


}
