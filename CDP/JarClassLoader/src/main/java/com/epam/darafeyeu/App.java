package com.epam.darafeyeu;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.log4j.Logger;

public class App
{
    // path to jar to load from
    //public final static String PATH_TO_JAR = "/home/xdar/TRAINING/MyClassLoader/target/classloader-1.0.0.jar";
    static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         logger.info("\n1 - Load class and invoke method;\n2 - Help;\n3 - Exit");

        try {
            for (;;) {
                int i = Integer.parseInt(br.readLine());
                switch (i) {

                    case 1:
                        //logger.info("Please input full path to jar to load from");
                        //String pathToJar = br.readLine();
                        loadFromJar();
                        break;

                    case 2:
                        logger.info("\n-loads 'ClassToLoad' class from classloader-1.0.0.jar \n-invokes 'sayHello' method with input String parameter 'Aliaksandr'\n jar should be built previously using 'mvn package' command");
                        break;

                    case 3:
                        System.exit(0);
                        break;
                }
            }
        } catch (NumberFormatException nfe) {
            System.err.println("Wrong input!");
        }
    }

    public static  void loadFromJar() throws  Exception{

        ClassLoader loader = new JarClassLoader();
        Class clazz = Class.forName("com.epam.darafeyeu.ClassToLoad", true, loader);
        IClassToLoad object = (IClassToLoad) clazz.newInstance();
        object.sayHello("Aliaksandr");
        logger.info("Calss loader is "+object.getClass().getClassLoader().toString());
    }
}
