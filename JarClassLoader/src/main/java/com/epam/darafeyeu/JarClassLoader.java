package com.epam.darafeyeu;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarClassLoader extends ClassLoader {

    static final Logger logger = Logger.getLogger(JarClassLoader.class);
    public final static String PATH_TO_JAR = "jarToLoadFrom.jar";


    public JarClassLoader(ClassLoader parent) {
        super(parent);
    }

    public JarClassLoader() {
        this(JarClassLoader.class.getClassLoader());
    }

    protected Class<?> findClass(final String name) throws ClassNotFoundException {

        Class result = findLoadedClass(name);
        if (result != null) {
            logger.info("% Class " + name + "found in cache");
            return result;
        }

        result = LoadClassFromJar(name);
        if (result == null) {
            result = super.findSystemClass(name);
        }
        return result;
    }

    private Class LoadClassFromJar(String name) throws ClassNotFoundException {

        JarFile jarFile = null;

        try {
            jarFile = new JarFile(PATH_TO_JAR);
        } catch (IOException e) {
            logger.error("Jar file " + PATH_TO_JAR + " not found" + e.getMessage());
        }

        Enumeration entries = jarFile.entries();
        while (entries.hasMoreElements()) {

           JarEntry jarEntry = (JarEntry) entries.nextElement();
           if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
               continue;
           }

           // get class name
           String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);
           className = className.replace('/', '.');

           if (className.equalsIgnoreCase(name)) {
              try {
                 byte[] byteArray = loadJarEntryAsBytes(jarFile, jarEntry);
                 return defineClass(className, byteArray, 0, byteArray.length);
              } catch (ClassFormatError e) {
                 throw new ClassNotFoundException();
              } catch (IOException ex) {
                 throw new ClassNotFoundException();
              }
           }
        }
        return null;
    }


    private static byte[] loadJarEntryAsBytes(JarFile jarFile, JarEntry jarEntry) throws IOException {

        DataInputStream dataInputStream = null;
        byte[] data = null;

        try {
            long size = jarEntry.getSize();
            if (size <= 0 || size >= Integer.MAX_VALUE) {
                return null;
            }

            data = new byte[(int) size];
            InputStream is = jarFile.getInputStream(jarEntry);
            dataInputStream = new DataInputStream(is);
            dataInputStream.readFully(data);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            throw new IOException();
        } finally {
           if (dataInputStream != null)
               try {
                   dataInputStream.close();
               } catch (IOException ex) {
                   logger.error(ex.getMessage());
                   throw new IOException();
               }
        }
        return data;
    }
}
