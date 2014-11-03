package com.epam.darafeyeu.services;

import com.epam.darafeyeu.Person;
import com.epam.darafeyeu.ThreadFileReader;
import com.epam.darafeyeu.dao.PersonDAO;
import com.epam.darafeyeu.exceptions.PersonNotFoundException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * PersonService class used to call CRUD operations
 */
public class PersonService {

    static final Logger logger = Logger.getLogger(PersonService.class);

    PersonDAO personDAO;

    public PersonService( PersonDAO personDAO ){
        this.personDAO = personDAO;
    }

    public Person getPerson( Integer personId ) throws PersonNotFoundException {
        Person person = personDAO.getPerson(personId);
        return person;
    }

    public List<Person> getAllPersons(){
        logger.info("Service getAllPersons");
        return personDAO.getAllPersons();
    }

    public void addPerson(Person person){
        logger.info("Service addPerson");
        personDAO.addPerson(person);
    }

    public void deletePerson(Integer personId) throws PersonNotFoundException {
        logger.info("Service deletePerson");
        personDAO.deletePerson(personId);
    }



}
