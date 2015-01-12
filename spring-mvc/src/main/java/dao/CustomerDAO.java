package dao;

import domain.Customer;

import java.util.List;

/**
 * Created by xdar on 8.1.15.
 */
public interface CustomerDAO {

    public List<Customer> getCustomers();

    public Customer getCustomerById(int id);

}
