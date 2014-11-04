package com.epam.darafeyeu;

import com.epam.darafeyeu.dao.PersonDAO;
import com.epam.darafeyeu.dao.PersonDAOImpl;
import com.epam.darafeyeu.services.PersonService;
import org.apache.log4j.Logger;
import com.epam.darafeyeu.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class. Get all persons in separate thread.
 */
public class BankModel {

    private static final Logger logger = Logger.getLogger(BankModel.class);

    public static void main(String[] args) {

         List<Person> persons = new ArrayList<Person>();

         //this object is synchronized between threads
         final PersonDAO personDAO = new PersonDAOImpl(args[0]);

         PersonService personService = new PersonService(personDAO);
         personService.addPerson(new Person(1,"Alex","Dorofeev"));

         GetAllPersonsConsumer  getAllPersonsConsumer  = new GetAllPersonsConsumer(personDAO, persons);
         getAllPersonsConsumer.start();

         try {
             getAllPersonsConsumer.join();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

         for (Person person : persons)
            logger.info(person.getFirstName());

    }
}
