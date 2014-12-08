package com.epam.darafeyeu.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 07.12.14.
 */

@Entity
@Table (name = "employee")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="EMPLOYEE_ID")
    private int id;

    @Column(name="EMPLOYEE_NAME")
    private String name;

    @Embedded
    private Address address;

    @OneToOne
    @JoinColumn(name="PINFO_ID")
    EmployeePersonalInfo employeePersonalInfo;

    @ManyToOne
    @JoinColumn(name="UNIT_ID")
    private Unit unit;

    @ManyToMany
    private Collection<Project> projects = new ArrayList<Project>();


    public Employee(){

    }

    public Employee(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public EmployeePersonalInfo getEmployeePersonalInfo() {
        return employeePersonalInfo;
    }

    public void setEmployeePersonalInfo(EmployeePersonalInfo employeePersonalInfo) {
        this.employeePersonalInfo = employeePersonalInfo;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
