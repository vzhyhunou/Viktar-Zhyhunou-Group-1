package com.epam.darafeyeu;

import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Producer class. Writes objects to file
 */
public class Producer  extends  Thread{
    static final Logger logger = Logger.getLogger(Producer.class);

    private final  DataFile dataFile;
    private final int number;
    private final CountDownLatch latch;

    public Producer ( DataFile dataFile, int number , CountDownLatch latch) {
        this.dataFile = dataFile;
        this.number = number;
        this.latch = latch;
    }

    public void run() {
        for ( int i = 0; i < 10; i++ ) {
            Person person = new Person( "First Name " + i, "Last Name " + i );
            dataFile.writeToFile(person);
            //logger.info("Producer #" + this.number + " wrote: " + person.getFirstName());
        }
        latch.countDown();
    }
}
