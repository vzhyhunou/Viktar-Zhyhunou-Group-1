package services;

import dao.CustomerDAO;
import domain.Customer;

import java.util.List;

/**
 * Created by xdar on 8.1.15.
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers(){
        return customerDAO.getCustomers();
    }

    public Customer getCustomerById(int id){
        return customerDAO.getCustomerById(id);
    }
}
