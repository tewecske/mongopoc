package com.ideologic.mongopoc.domain.customer;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author: tewe
 */
@Test
public class CustomerTest {

    @Test
    public void testInit() throws Exception {
        Customer customer = new Customer();
        customer.setName("Test Person");
        customer.setEmail("test@person.com");

        Assert.assertEquals(customer.toString(), "Test Person (test@person.com)", "toString should print name and email");

    }
}
