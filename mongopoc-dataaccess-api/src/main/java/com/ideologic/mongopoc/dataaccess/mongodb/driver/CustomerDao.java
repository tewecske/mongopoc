package com.ideologic.mongopoc.dataaccess.mongodb.driver;

import com.ideologic.mongopoc.domain.customer.Customer;

/**
 * @author: tewe
 */
public interface CustomerDao {

    void create(Customer customer);

}
