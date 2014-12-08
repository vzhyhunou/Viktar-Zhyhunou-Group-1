package com.epam.darafeyeu.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 07.12.14.
 */
@Entity
public class Unit {

    @Id @GeneratedValue
    @Column(name="UNIT_ID")
    private int id;

    private String name;


    @OneToMany(mappedBy = "unit")
    private Collection<Employee> employees = new ArrayList<Employee>();

    public Unit(){

    }
    public Unit(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

}
