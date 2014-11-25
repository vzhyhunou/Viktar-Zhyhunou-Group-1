package com.epam.ear.customers;

import javax.ejb.Remote;
import java.util.List;


@Remote
public interface CustomerManager {
    void createCustomer(String name);

    List<Customer> list();
}

