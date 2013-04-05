package com.ideologic.mongopoc.domain.customer;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author tewe
 */
@Test
public class AddressTest {

    @Test
    public void testInit() throws Exception {
        Address address = new Address();
        address.setCity("Budapest");
        address.setAddress("Small str. 12.");

        Assert.assertEquals(address.toString(), "Budapest, Small str. 12.", "toString should city and address");


    }
}
