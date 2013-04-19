package com.ideologic.mongopoc.dataaccess.morphia;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Morphia;
import com.github.jmkgreen.morphia.mapping.DefaultMapper;
import com.github.jmkgreen.morphia.mapping.MappedClass;
import com.github.jmkgreen.morphia.mapping.Mapper;
import com.ideologic.mongopoc.configuration.MongopocConfiguration;
import com.ideologic.mongopoc.domain.customer.Customer;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;

import static org.testng.Assert.assertEquals;

/**
 * @author tewe
 */
public class MorphiaCustomerDaoTest {

    private Datastore datastore;
    private MorphiaCustomerDao morphiaCustomerDao;

    @BeforeClass
    public void beforeClass() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(MongopocConfiguration.MONGOPOC_HOST, MongopocConfiguration.MONGOPOC_PORT);

        datastore = new Morphia(new HashSet<Class>(Arrays.asList(MorphiaCustomer.class)))
                .createDatastore(mongoClient, MongopocConfiguration.MONGOPOC_DATABASE_NAME);

        datastore.getCollection(MorphiaCustomer.class).drop();
        morphiaCustomerDao = new MorphiaCustomerDao();
        morphiaCustomerDao.setDatastore(datastore);
    }

    @Test
    public void testCreate() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerNumber(1L);
        customer.setName("Mor Phia");
        customer.setEmail("mor@phia.com");

        morphiaCustomerDao.create(customer);

        MorphiaCustomer actual = datastore.find(MorphiaCustomer.class, "name", "Mor Phia").get();

        assertEquals(actual.getName(), customer.getName());
        assertEquals(actual.getEmail(), customer.getEmail());
    }

    @Test
    public void testUpdate() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerNumber(1L);
        customer.setName("Mor Phia");
        customer.setEmail("mor@phia.com");

        morphiaCustomerDao.create(customer);
        customer.setEmail("updated@email.com");
        morphiaCustomerDao.update(customer);

        MorphiaCustomer actual = datastore.find(MorphiaCustomer.class, "name", "Mor Phia").get();

        assertEquals(actual.getName(), customer.getName());
        assertEquals(actual.getEmail(), "updated@email.com");
    }
}
