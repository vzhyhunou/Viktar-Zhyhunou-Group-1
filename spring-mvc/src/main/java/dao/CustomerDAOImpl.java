package dao;

import domain.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdar on 8.1.15.
 */
public class CustomerDAOImpl implements CustomerDAO {

    private List<Customer> customers;

    public CustomerDAOImpl(){

        customers = new ArrayList<Customer>();
        customers.add(new Customer(1,"Mike"));
        customers.add(new Customer(2,"Alex"));
    }

    @Override
    public List<Customer> getCustomers() {
        return this.customers;
    }

    @Override
    public Customer getCustomerById(int id) {

        for (Customer customer : customers){
            if (customer.getId() == id){
                return customer;
            }
        }

        return null;
    }
}
