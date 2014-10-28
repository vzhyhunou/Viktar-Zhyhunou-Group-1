package com.epam.darafeyeu;

import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Consumer class. Reads objects from file
 */
public class Consumer extends Thread{

    static final Logger logger = Logger.getLogger(Consumer.class);

    private final DataFile dataFile;
    private final int number;
    private final CountDownLatch latch;

    public Consumer(DataFile c, int number, CountDownLatch latch) {
        this.dataFile = c;
        this.number = number;
        this.latch = latch;
    }

    public void run() {
        for ( int i = 0; i < 10; i++ ) {
            Person person = dataFile.readFromFile(i);
            logger.info("Consumer #" + this.number + " read: " + person.getFirstName());
        }
        latch.countDown();
    }
}
