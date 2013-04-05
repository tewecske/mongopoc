package com.ideologic.mongopoc.domain.customer;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author tewe
 */
@Test
public class CustomerTest {

    @Test
    public void testInit() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerNumber(1L);
        customer.setName("Test Person");
        customer.setEmail("test@person.com");

        Assert.assertEquals(customer.toString(), "[1] Test Person (test@person.com)", "toString should print customerNumber, name and email");

    }
}
