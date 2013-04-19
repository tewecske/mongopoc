package com.ideologic.mongopoc.dataaccess.morphia;

import com.github.jmkgreen.morphia.Datastore;
import com.ideologic.mongopoc.dataaccess.mongodb.driver.CustomerDao;
import com.ideologic.mongopoc.domain.customer.Customer;

/**
 * @author tewe
 */
public class MorphiaCustomerDao implements CustomerDao {

    private Datastore datastore;


    @Override
    public void create(Customer customer) {
        MorphiaCustomer morphiaCustomer = convert(customer);
        datastore.save(morphiaCustomer);
    }

    private MorphiaCustomer convert(Customer customer) {
        MorphiaCustomer morphiaCustomer = new MorphiaCustomer();
        morphiaCustomer.setCustomerNumber(customer.getCustomerNumber());
        morphiaCustomer.setName(customer.getName());
        morphiaCustomer.setEmail(customer.getEmail());
        morphiaCustomer.setAddress(customer.getAddress());
        return morphiaCustomer;
    }

    @Override
    public void update(Customer customer) {
        MorphiaCustomer morphiaCustomer = convert(customer);
        datastore.save(morphiaCustomer);
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }
}
