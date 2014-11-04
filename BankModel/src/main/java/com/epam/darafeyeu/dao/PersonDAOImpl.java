package com.epam.darafeyeu.dao;

import com.epam.darafeyeu.domain.Person;
import com.epam.darafeyeu.exceptions.PersonNotFoundException;
import com.epam.darafeyeu.utils.BankModelUtils;
import org.apache.log4j.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  PersonDAO Implementaion. Data file acts as a storage
 */
public class PersonDAOImpl implements PersonDAO
{
    private static final Logger logger = Logger.getLogger(PersonDAOImpl.class);
    final String FILENAME;

    public PersonDAOImpl(final String FILENAME) {
        this.FILENAME = FILENAME;
    }

    @Override
    public synchronized List<Person> getAllPersons() {

        logger.info("DAO getAllPersons");

        List<Person> persons = new ArrayList<Person>();
        ObjectInputStream objectInputStream = null;

        Person person = null;

        try {
            objectInputStream = BankModelUtils.getObjectInputStream(FILENAME);
            do {
                person = (Person) objectInputStream.readObject();
                if ( person != null )
                    persons.add(person);
            }
            while ( person != null );
        } catch(IOException ex){}
          catch(ClassNotFoundException ex){}
          finally {
            BankModelUtils.closeObjectInputStream(objectInputStream);
        }
        return persons;
    }

    @Override
    public synchronized Person getPerson(Integer personId) throws PersonNotFoundException {

        ObjectInputStream objectInputStream = null;

        Person person = null;
        Person pers;

        try {
            objectInputStream = BankModelUtils.getObjectInputStream(FILENAME);
            do {

                pers = (Person) objectInputStream.readObject();
                if ( pers.getId().intValue() == personId ){
                    person = pers;
                    break;
                }

            }
            while ( pers != null );

        } catch(IOException ex){}
          catch(ClassNotFoundException ex){}
          finally {
            BankModelUtils.closeObjectInputStream(objectInputStream);
            if ( person == null ){
                throw new PersonNotFoundException("Person with given Id is not found!");
            }
        }
        return person;
    }

    @Override
    public void addPerson(Person person) {
        List<Person> persons = new ArrayList<Person>();

        //logger.info("start of writeToFile "+ person.getFirstName());

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        Person pers;
        try {
            objectInputStream = BankModelUtils.getObjectInputStream(FILENAME);
            do {
                pers = (Person) objectInputStream.readObject();
                if ( pers != null )
                   persons.add(pers);
            }
            while ( pers != null );
        } catch (IOException ex){}
        catch (ClassNotFoundException ex){}
        finally {
            BankModelUtils.closeObjectInputStream(objectInputStream);
        }

        persons.add(person);
        //Collections.sort(persons, new Comparators());
        BankModelUtils.sortPersons(persons);

        try {
            objectOutputStream = BankModelUtils.getObjectOutputStream(FILENAME);
            for (Person p : persons){
                objectOutputStream.writeObject(p);
            }
        } catch (IOException ex){}
        finally {
            BankModelUtils.closeObjectOutputStream(objectOutputStream);
        }
    }

    @Override
    public synchronized void deletePerson(Integer personId) throws PersonNotFoundException {

        List<Person> persons = new ArrayList<Person>();

        logger.info("DeletePerson");

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        Person person = null;
        Person pers;
        try {
            objectInputStream = BankModelUtils.getObjectInputStream(FILENAME);
            do {

                pers = (Person) objectInputStream.readObject();
                if ( pers.getId() == personId ){
                    person = pers;
                } else {
                    persons.add(pers);
                }
            }
            while ( pers != null );
        } catch (IOException ex){}
          catch (ClassNotFoundException ex){}
          finally {
            BankModelUtils.closeObjectInputStream(objectInputStream);
            if ( person == null ){
                throw new PersonNotFoundException("Person with given Id is not found!");
            }
        }

        try {
            objectOutputStream = BankModelUtils.getObjectOutputStream(FILENAME);
            for (Person p : persons){
               objectOutputStream.writeObject(p);
            }
        } catch (IOException ex){}
          finally {
            BankModelUtils.closeObjectOutputStream(objectOutputStream);
        }
    }
}
