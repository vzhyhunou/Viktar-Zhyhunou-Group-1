package com.epam.darafeyeu.domain;

import java.io.Serializable;

/**
 * Class that represents person's data
 * Used in person's account
 */
public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private Integer id;

    public Person(Integer id, String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
