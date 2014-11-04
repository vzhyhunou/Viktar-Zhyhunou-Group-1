package com.epam.darafeyeu.utils;

import com.epam.darafeyeu.domain.Person;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

/**
 *  Utils for Person service
 */
public class BankModelUtils {

    final static Logger logger = Logger.getLogger(BankModelUtils.class);

    public static File getFile(final String filename){

        File file = new File(filename);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                logger.info(ex.getMessage());
            }
        }
        return file;
    }

    public static ObjectInputStream getObjectInputStream(final String FILENAME) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(getFile(FILENAME));
        return new ObjectInputStream(fileInputStream);
    }

    public static ObjectOutputStream getObjectOutputStream(final String FILENAME) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(getFile(FILENAME));
        return new ObjectOutputStream(fileOutputStream);
    }

    public static void closeObjectInputStream(ObjectInputStream objectInputStream){
        if (objectInputStream != null){
            try{
                objectInputStream.close();
            } catch (IOException ex){}
        }
    }

    public static void closeObjectOutputStream(ObjectOutputStream objectOutputStream){
        if (objectOutputStream != null){
            try{
                objectOutputStream.close();
            } catch (IOException ex){}
        }
    }

    public static void sortPersons(List<Person> persons) {
        Collections.sort(persons, new SortByIdComparator());
    }
}
