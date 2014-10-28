package com.epam.darafeyeu;

import java.io.*;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

/**
 * This program represents two threads: the first one  writes ten objects to file
 * and the second reads this objects from this file. Threads work sequentially.
 * It means that after the write of the first thread the second will read data.
 * It can't be written two objects  without reading by second thread, but different options are possible (in progress)
 */

public class ThreadFileReader {

    static final Logger logger = Logger.getLogger(ThreadFileReader.class);
    final static String FILENAME = "data.dat";

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(2);

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

            datafile = new DataFile(objectOutputStream, objectInputStream);

            Producer p1 = new Producer(datafile, 1, latch);
            //Producer p2 = new Producer(datafile, 2);
            Consumer c1 = new Consumer(datafile, 1, latch);
            //Consumer c2 = new Consumer(datafile, 2);
            p1.start();
            //p2.start();
            c1.start();
            //c2.start();


            //wait for threads to complete all the work to prevent premature closing of streams
            try{
                latch.await();
                logger.info("Threads finished work!!!");
            }catch(InterruptedException ex){
                logger.info(ex.getMessage());
            }

        } catch(IOException ex){
            logger.info(ex.getMessage());
        } finally {

            if (objectOutputStream != null){
                try{
                    objectOutputStream.close();
                } catch (IOException ex){}
            }
            if (objectInputStream != null){
                try{
                    objectInputStream.close();
                } catch (IOException ex){}
            }
        }
    }
}

