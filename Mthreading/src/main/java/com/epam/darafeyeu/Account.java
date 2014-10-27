package com.epam.darafeyeu;

import java.io.Serializable;

/**
 * Class that reperesents person's account
 * Used to write to and read from file
 */
public class Account implements Serializable {
    private Person person;
    private boolean locked;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
