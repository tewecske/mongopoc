package com.ideologic.mongopoc.dataaccess.jongo;

import com.ideologic.mongopoc.dataaccess.mongodb.driver.CustomerDao;
import com.ideologic.mongopoc.domain.customer.Customer;
import org.jongo.MongoCollection;

/**
 * @author tewe
 */
public class JongoCustomerDao implements CustomerDao {

    private MongoCollection customerCollection;

    @Override
    public void create(final Customer customer) {
        customerCollection.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerCollection.save(customer);
    }

    public void setCustomerCollection(final MongoCollection customerCollection) {
        this.customerCollection = customerCollection;
    }
}
