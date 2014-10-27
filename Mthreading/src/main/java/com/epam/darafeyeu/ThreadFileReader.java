package com.epam.darafeyeu;

import java.io.*;
import org.apache.log4j.Logger;

/**
 * This program represents two threads: the first one  writes ten objects to file
 * and the second reads this objects from this file. Threads work sequentially.
 * It means that after the write of the first thread the second will read data.
 * It can't be written two objects  with out reading by second thread, but different options are possible (in progress)
 */

public class ThreadFileReader {

    static final Logger logger = Logger.getLogger(ThreadFileReader.class);

    public static void main(String[] args) {

        final String FILENAME = "data.dat";

        FileOutputStream fileOutputStream = null ;
        ObjectOutputStream objectOutputStream = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        DataFile datafile;

        File file = new File(FILENAME);

        if ( !file.exists() ) {
            try {
                file.createNewFile();
            } catch(IOException ex){
                logger.info(ex.getMessage());
            }
        }

        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch(IOException ex){
            logger.info(ex.getMessage());
        }


        if ( fileOutputStream != null && objectOutputStream != null ) {

            datafile = new DataFile(objectOutputStream, objectInputStream);

            Producer p1 = new Producer(datafile, 1);
            //Producer p2 = new Producer(datafile, 2);
            Consumer c1 = new Consumer(datafile, 1);
            //Consumer c2 = new Consumer(datafile, 2);
            p1.start();
            //p2.start();
            c1.start();
            //c2.start();


            //wait for threads to complete all the work
            try {
                p1.join();
            } catch (InterruptedException ex) {
                logger.info(ex.getMessage());
            }

            try {
                c1.join();
            } catch (InterruptedException ex) {
                logger.info(ex.getMessage());
            }

            // close streams
            try {
                objectOutputStream.close();
            } catch(IOException ex){
                logger.info(ex.getMessage());
            }

            try {
                objectInputStream.close();
            } catch(IOException ex){
                logger.info(ex.getMessage());
            }
        }
    }
}

class DataFile {

    static final Logger logger = Logger.getLogger(ThreadFileReader.class);

    private boolean availableForReading = false;

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    DataFile (ObjectOutputStream objectOutputStream , ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public synchronized Person readFromFile( int i ) {

        Person person = null;

        logger.info("start of GET "+i);

        while ( !availableForReading ) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }

        logger.info("GET " + i + " right after wait");

        availableForReading = false;
        notifyAll();

        try {
            person = (Person) objectInputStream.readObject();
        } catch (IOException ex) {}
          catch (ClassNotFoundException ex){}

        logger.info("readFromFile method is about to release synchronized section");
        return person;
    }

    public synchronized void writeToFile( Person person ) {

        System.out.println("start of PUT "+ person.getFirstName());

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
        } catch(FileNotFoundException ex){
            logger.info(ex.getMessage());
        } catch(IOException ex){
            logger.info(ex.getMessage());
        }

        availableForReading = true;
        notifyAll();
        logger.info("The end of PUT");
    }
}

class Consumer extends Thread {

    static final Logger logger = Logger.getLogger(Consumer.class);

    private DataFile dataFile;
    private int number;

    public Consumer(DataFile c, int number) {
        this.dataFile = c;
        this.number = number;
    }

    public void run() {
        for ( int i = 0; i < 10; i++ ) {
            Person person = dataFile.readFromFile(i);

            logger.info("Consumer #" + this.number + " read: " + person.getFirstName());
        }
    }
}

class Producer extends Thread {

    static final Logger logger = Logger.getLogger(Producer.class);

    private DataFile dataFile;
    private int number;

    public Producer ( DataFile dataFile, int number ) {
        this.dataFile = dataFile;
        this.number = number;
    }

    public void run() {
        for ( int i = 0; i < 10; i++ ) {
            Person person = new Person( "First Name " + i, "Last Name " + i );
            dataFile.writeToFile(person);
            logger.info("Producer #" + this.number + " wrote: " + person.getFirstName());
//            try {
//                sleep(200);
//            } catch (InterruptedException e) { }
        }
    }

}