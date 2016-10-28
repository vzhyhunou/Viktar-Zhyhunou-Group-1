package com.epam.darafeyeu.dao;

import com.epam.darafeyeu.domain.Person;
import com.epam.darafeyeu.exceptions.PersonNotFoundException;

import java.util.List;

/**
 * common DAO interface
 */
public interface PersonDAO {

    public List<Person> getAllPersons()  ;

    public Person getPerson(Integer personId) throws PersonNotFoundException;

    public void addPerson(Person person) ;

    //rel-7

    public void  deletePerson(Integer id) throws PersonNotFoundException;
}
