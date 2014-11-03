package com.epam.darafeyeu;

import com.epam.darafeyeu.dao.PersonDAO;
import com.epam.darafeyeu.dao.PersonDAOImpl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 *
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

//        ExecutorService executor = Executors.newFixedThreadPool(1);
//       Future<List<Person>> task = executor.submit( new GetAllPersonsConsumer( new PersonDAOImpl() ) );

         //blocks until result is ready
//        try {
//            persons = task.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if ( persons != null ) {
//            for (Person person : persons)
//                logger.info(person.getFirstName());
//        }
//
//
//        Person person = null;
//        Future<Person> task2 = executor.submit( new GetByIdConsumer( new PersonDAOImpl(),5 ) );
//
//        try {
//            person = task2.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (person != null){
//            logger.info(person.getFirstName());
//        }

    }
}
