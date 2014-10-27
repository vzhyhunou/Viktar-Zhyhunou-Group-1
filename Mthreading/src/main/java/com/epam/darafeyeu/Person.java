package com.epam.darafeyeu;

import java.io.Serializable;

/**
 * Class that represents person's data
 * Used in person's account
 */
public class Person implements Serializable {
    private String firstName;
    private String lastname;

    Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastname = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
