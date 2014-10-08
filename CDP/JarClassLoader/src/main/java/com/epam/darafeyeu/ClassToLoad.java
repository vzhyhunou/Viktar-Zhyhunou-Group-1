package com.epam.darafeyeu;

import org.apache.log4j.Logger;

public class ClassToLoad  implements IClassToLoad {

    static final Logger logger = Logger.getLogger(ClassToLoad.class);

    public void sayHello(final String name){

        logger.info("Hello " + name);
    }

}
