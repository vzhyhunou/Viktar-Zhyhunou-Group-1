package com.epam.darafeyeu;

import com.epam.darafeyeu.dao.PersonDAO;
import com.epam.darafeyeu.dao.PersonDAOImpl;
import org.apache.log4j.Logger;
import com.epam.darafeyeu.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class. Get all persons in separate thread.
 */
public class BankModel {

    static final Logger logger = Logger.getLogger(BankModel.class);
    static final String FILENAME = "/home/xdar/data.dat";

    //this object is synchronized between threads
    static final PersonDAO personDAO = new PersonDAOImpl(FILENAME);

    public static void main(String[] args) {

         List<Person> persons = new ArrayList<Person>();

         GetAllPersonsConsumer  getAllPersonsConsumer  = new GetAllPersonsConsumer(personDAO, persons);
         getAllPersonsConsumer.start();

         try {
             getAllPersonsConsumer.join();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

         if ( persons != null ) {
            for (Person person : persons)
                logger.info(person.getFirstName());
         }
    }
}
