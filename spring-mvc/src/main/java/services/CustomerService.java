package services;

import domain.Customer;

import java.util.List;

/**
 * Created by xdar on 9.1.15.
 */
public interface CustomerService {

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(int id);

}
