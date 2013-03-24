package com.ideologic.mongopoc.domain.customer;

/**
 * @author: tewe
 */
public class Customer {

    private String name;
    private String email;

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

    @Override
    public String toString() {
        return name + " (" + email + ')';
    }
}
