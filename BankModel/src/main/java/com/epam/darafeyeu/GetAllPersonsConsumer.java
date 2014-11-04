package com.epam.darafeyeu;

import com.epam.darafeyeu.dao.PersonDAO;
import com.epam.darafeyeu.domain.Person;
import com.epam.darafeyeu.services.PersonService;

import java.util.List;

/**
 * Class that represents getting all persons in separate thread
 */

public class GetAllPersonsConsumer extends Thread {

    private PersonService personService = null;
    private List<Person> persons;

    public GetAllPersonsConsumer(PersonDAO personDAO, List<Person> persons){
        this.personService = new PersonService(personDAO);
        this.persons = persons;
    }

    public void run() {
        persons.addAll(personService.getAllPersons());
    }
}