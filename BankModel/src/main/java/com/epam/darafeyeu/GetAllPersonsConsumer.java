package com.epam.darafeyeu;

import com.epam.darafeyeu.dao.PersonDAO;
import com.epam.darafeyeu.dao.PersonDAOImpl;
import com.epam.darafeyeu.services.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 */
//public class GetAllPersonsConsumer  implements Callable<List<Person>> {
//
//    PersonService personService = null;
//
//    public GetAllPersonsConsumer(PersonDAOImpl personDAO){
//        this.personService = new PersonService(personDAO);
//    }
//
//    public List<Person> call() {
//        return personService.getAllPersons();
//    }
//}

public class GetAllPersonsConsumer  extends  Thread
{

    PersonService personService = null;
    List<Person> persons;

    public GetAllPersonsConsumer(PersonDAO personDAO, List<Person> persons){
        this.personService = new PersonService(personDAO);
        this.persons = persons;
    }

    public void run() {
        persons.addAll(personService.getAllPersons());
    }
}