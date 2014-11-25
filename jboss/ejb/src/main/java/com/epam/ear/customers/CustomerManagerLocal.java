package com.epam.ear.customers;

import javax.ejb.Local;
import java.util.List;


@Local
public interface CustomerManagerLocal {
    void createCustomer(String name);

    List<Customer> list();
}
