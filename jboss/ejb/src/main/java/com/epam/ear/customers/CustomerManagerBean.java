package com.epam.ear.customers;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
@DeclareRoles("bean")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CustomerManagerBean implements CustomerManager, CustomerManagerLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    @RolesAllowed("bean")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createCustomer(String name) {
        Customer customer = new Customer(name);
        em.persist(customer);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Customer> list() {
        Query query = em.createQuery("from Customer");
        return query.getResultList();
    }
}
