package com.epam.darafeyeu;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class MemoryEater {

    static final Logger logger = Logger.getLogger("MemoryEater.class");
    static final int MEGABYTES_TO_BYTES_MULTIPLIER = 1024 * 1024;
    // the less ratio is the less often refs are deleted and the more heap memory is used
    static final double TOTAL_MEM_RATIO = 0.01;

    public static void main(String[] args) {

        List v = new ArrayList();

        while (true) {

            Runtime rt = Runtime.getRuntime();
            byte b[] = null;
            try {
                logger.error("Before NEW - FREE: " + rt.freeMemory() + "  TOTAL: " + rt.totalMemory());
                b = new byte[10 * MEGABYTES_TO_BYTES_MULTIPLIER];
            } catch (OutOfMemoryError ex){
                logger.error("Exception occured after NEW! current FREE: " + rt.freeMemory() + "  TOTAL: " + rt.totalMemory());
                System.exit(0);
            }

            //try to remove refs as less often as possible
            if ( rt.totalMemory() % rt.freeMemory() < rt.totalMemory() * TOTAL_MEM_RATIO )
            // one more way
            //if ( rt.freeMemory() < rt.totalMemory() * TOTAL_MEM_RATIO )
            {
                logger.info("Deleted "+v.size()+" ref(s). Total size " + 10 * MEGABYTES_TO_BYTES_MULTIPLIER * v.size() +" bytes");
                v.clear();
            }

            v.add(b);

            logger.info("After NEW - FREE: " + rt.freeMemory()+"  TOTAL: "+rt.totalMemory());

            //delay to investigate curve in Visual VM
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
            }
        }
    }
}
