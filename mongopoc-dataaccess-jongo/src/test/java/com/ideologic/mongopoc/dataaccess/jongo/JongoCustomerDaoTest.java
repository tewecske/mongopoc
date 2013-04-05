package com.ideologic.mongopoc.dataaccess.jongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideologic.mongopoc.domain.customer.Customer;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.Mapper;
import org.jongo.MongoCollection;
import org.jongo.ReflectiveObjectIdUpdater;
import org.jongo.marshall.jackson.JacksonIdFieldSelector;
import org.jongo.marshall.jackson.JacksonMapper;
import org.jongo.marshall.jackson.bson4jackson.MongoBsonFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.net.UnknownHostException;

import static org.testng.Assert.assertEquals;

/**
 * @author tewe
 */
@Test
public class JongoCustomerDaoTest {

    private DB db;
    private MongoCollection customerCollection;
    private JongoCustomerDao jongoCustomerDao;

    private class CustomerIdFieldSelector extends JacksonIdFieldSelector {
        @Override
        public boolean isId(Field f) {
            return super.isId(f) || "customerNumber".equals(f.getName());
        }
    }

    abstract class MixIn {
        @JsonProperty("_id") abstract int getCustomerNumber();
    }

    @BeforeClass
    public void beforeClass() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost", 27770);
        db = mongoClient.getDB("mongopoc");

        ObjectMapper objectMapper = new ObjectMapper(MongoBsonFactory.createFactory());
        objectMapper.addMixInAnnotations(Customer.class, MixIn.class);

        Mapper mapper = new JacksonMapper.Builder(objectMapper)
                .withObjectIdUpdater(new ReflectiveObjectIdUpdater(new CustomerIdFieldSelector()))
                .build();
        Jongo jongo = new Jongo(db, mapper);
        customerCollection = jongo.getCollection("customer");
        jongoCustomerDao = new JongoCustomerDao();
        jongoCustomerDao.setCustomerCollection(customerCollection);
        customerCollection.drop();
    }


    @Test
    public void testCreate() {
        Customer customer = new Customer();
        customer.setCustomerNumber(1L);
        customer.setName("Jon Go");
        customer.setEmail("jon@go.com");

        jongoCustomerDao.create(customer);

        Customer actual = customerCollection.findOne("{'name':#}", "Jon Go").as(Customer.class);

        assertEquals(actual.getName(), customer.getName());
        assertEquals(actual.getEmail(), customer.getEmail());
    }

    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setCustomerNumber(1L);
        customer.setName("Jon Go");
        customer.setEmail("jon@go.com");

        jongoCustomerDao.create(customer);
        customer.setEmail("updated@email.com");
        jongoCustomerDao.update(customer);

        Customer actual = customerCollection.findOne("{'name':#}", "Jon Go").as(Customer.class);

        assertEquals(actual.getName(), customer.getName());
        assertEquals(actual.getEmail(), "updated@email.com");
    }


}
