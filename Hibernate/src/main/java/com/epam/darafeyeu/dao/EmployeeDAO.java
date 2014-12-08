package com.epam.darafeyeu.dao;

import com.epam.darafeyeu.domain.Employee;
import com.epam.darafeyeu.domain.EmployeeStatus;

/**
 * Created by Alex on 08.12.14.
 */
public interface EmployeeDAO {
    public Integer createEmployee(String name, String city, String street, EmployeeStatus employeeStatus);
    public Employee findEmployeeById(int id);
    public void deleteEmployeeById(int id);
    public void updateEmployeeById(int id, String name, String city, String street, EmployeeStatus employeeStatus);
    public void addEmployeeToUnit(int employeeId, int unitId);
    public void assignEmployeeForProject(int employeeId, int projectId);
}
