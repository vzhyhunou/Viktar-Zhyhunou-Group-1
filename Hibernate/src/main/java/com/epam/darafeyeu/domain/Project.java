package com.epam.darafeyeu.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 07.12.14.
 */
@Entity
public class Project {

    @Id @GeneratedValue
    private int id;

    private String name;



    @ManyToMany(mappedBy = "projects")
    private Collection<Employee> employees = new ArrayList<Employee>();

    public Project() {

    }

    public Project(String name) {
        this.name = name;
    }


    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
