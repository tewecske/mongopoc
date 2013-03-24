package com.ideologic.mongopoc.dataaccess.mongodb.driver;

import com.ideologic.mongopoc.domain.customer.Customer;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @author: tewe
 */
public class MongoDriverCustomerDao implements CustomerDao {

    private DBCollection customerCollection;

    @Override
    public void create(final Customer customer) {

        final DBObject customerDbObject = BasicDBObjectBuilder
                .start("name", customer.getName())
                .append("email", customer.getEmail())
                .get();

        customerCollection.insert(customerDbObject);

        CommandResult lastError = customerCollection.getDB().getLastError();
        if (!lastError.ok()) {
            throw new RuntimeException("Customer " + customer  + " create error "+lastError.getErrorMessage(), lastError.getException());
        }

    }

    public void setCustomerCollection(final DBCollection customerCollection) {
        this.customerCollection = customerCollection;
    }
}
