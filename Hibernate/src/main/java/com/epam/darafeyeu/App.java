package com.epam.darafeyeu;

import java.util.Date;

import com.epam.darafeyeu.dao.*;
import com.epam.darafeyeu.domain.*;
import org.hibernate.Session;
import com.epam.darafeyeu.persistence.HibernateUtil;

public class App 
{
    public static void main( String[] args )
    {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        UnitDAO unitDAO = new UnitDAOImpl();
        ProjectDAO projectDAO = new ProjectDAOImpl();
        Integer employeeId = employeeDAO.createEmployee("Alex Dorofeev", "Brest",
                "Morozova", EmployeeStatus.MIDDLE);

        employeeDAO.updateEmployeeById(employeeId, "Alex Dorofeev", "Brest",
                "Urogajnaya", EmployeeStatus.SENIOR);

        Integer unitId = unitDAO.createUnit("UnitName 1");

        employeeDAO.addEmployeeToUnit(employeeId, unitId);

        Integer projectId = projectDAO.createProject("IHG");

        employeeDAO.assignEmployeeForProject(employeeId, projectId);

        Employee employee = employeeDAO.findEmployeeById(employeeId);

        // diaplay assigned projects
        for(Project project : employee.getProjects())
            System.out.println(project.getName());

        employeeDAO.deleteEmployeeById(employeeId);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.close();
        HibernateUtil.shutdown();




    }
}
