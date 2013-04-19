package com.ideologic.mongopoc.dataaccess.morphia;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;
import com.ideologic.mongopoc.domain.customer.Address;

/**
 * @author tewe
 */
@Entity("customers")
public class MorphiaCustomer {

    @Id
    private Long customerNumber;
    private String name;
    private String email;

    private Address address;

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "[" + customerNumber + "] " + name + " (" + email + ')';
    }
}
