package com.epam.darafeyeu.exceptions;

/**
 * Custom PersonNotFoundException is thrown if person object is null
 */
public class PersonNotFoundException  extends Exception {

    private String message;

    public PersonNotFoundException(){
        super();
        message = "unknown";
    }

    public PersonNotFoundException(String message){
        super(message);
        this.message = message;
    }

    String getError(){
        return message;
    }

}
