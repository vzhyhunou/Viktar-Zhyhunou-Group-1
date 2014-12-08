package com.epam.darafeyeu.dao;

import com.epam.darafeyeu.domain.*;
import com.epam.darafeyeu.domain.EmployeeStatus;
import com.epam.darafeyeu.persistence.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * Created by Alex on 08.12.14.
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public Integer createEmployee(String name, String city, String street, EmployeeStatus employeeStatus ) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Employee employee = new Employee(name);

        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);

        employee.setAddress(address);

        EmployeePersonalInfo employeePersonalInfo = new EmployeePersonalInfo();
        employeePersonalInfo.setEmployeeStatus(employeeStatus);

        employee.setEmployeePersonalInfo(employeePersonalInfo);

        Integer result = (Integer) session.save(employee);
        session.save(employeePersonalInfo);
        session.getTransaction().commit();
        return result;
    }

    @Override
    public Employee findEmployeeById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee result = (Employee) session.get(Employee.class, id);
        //force to initialize list of projects
        Hibernate.initialize(result.getProjects());
        session.getTransaction().commit();
        return result;
    }

    @Override
    public void deleteEmployeeById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee employee = (Employee) session.get(Employee.class, id);
        session.delete(employee);
        session.getTransaction().commit();
    }

    @Override
    public void updateEmployeeById(int id, String name, String city, String street, EmployeeStatus employeeStatus) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee employee = (Employee) session.get(Employee.class, id);
        employee.setName(name);
        employee.getAddress().setCity(city);
        employee.getAddress().setStreet(street);
        employee.getEmployeePersonalInfo().setEmployeeStatus(employeeStatus);
        session.saveOrUpdate(employee);
        session.getTransaction().commit();
    }

    @Override
    public void addEmployeeToUnit(int employeeId, int unitId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee employee = (Employee) session.get(Employee.class, employeeId);
        Unit unit = (Unit) session.get(Unit.class, unitId);
        employee.setUnit(unit);
        unit.getEmployees().add(employee);
        session.getTransaction().commit();
    }

    @Override
    public void assignEmployeeForProject(int employeeId, int projectId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee employee = (Employee) session.get(Employee.class, employeeId);
        Project project = (Project) session.get(Project.class, projectId);
        employee.getProjects().add(project);
        project.getEmployees().add(employee);
        session.getTransaction().commit();
    }
}
