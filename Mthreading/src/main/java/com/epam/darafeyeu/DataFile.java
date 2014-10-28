package com.epam.darafeyeu;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class that represents shared file resource
 */
public class DataFile {

    static final Logger logger = Logger.getLogger(DataFile.class);

    private boolean availableForReading = false;

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    DataFile (ObjectOutputStream objectOutputStream , ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public synchronized Person readFromFile( int i ) {

        Person person = null;

        logger.info("start of readFromFile "+i);

        while ( !availableForReading ) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }

        logger.info("readFromFile " + i + " right after wait");

        availableForReading = false;
        notifyAll();

        try {
            person = (Person) objectInputStream.readObject();
        } catch (IOException ex) {
            logger.info(ex.getMessage());
        } catch (ClassNotFoundException ex){
            logger.info(ex.getMessage());
        }

        logger.info("readFromFile method is about to release synchronized section");
        return person;
    }

    public synchronized void writeToFile( Person person ) {

        logger.info("start of writeToFile "+ person.getFirstName());

        while ( availableForReading ) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }

        logger.info("writeToFile  " + person.getFirstName() + " right after wait");

        try {
            objectOutputStream.writeObject(person);
        } catch(IOException ex){
            logger.info(ex.getMessage());
        }

        availableForReading = true;
        notifyAll();
        logger.info("The end of writeToFile");
    }
}
