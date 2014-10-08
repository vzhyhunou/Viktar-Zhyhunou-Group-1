package com.epam.darafeyeu;

import org.apache.log4j.Logger;

public class ClassToLoadImpl  implements ClassToLoad {

    static final Logger logger = Logger.getLogger(ClassToLoad.class);

    public void sayHello(final String name){

        logger.info("Hello " + name);
    }

}
