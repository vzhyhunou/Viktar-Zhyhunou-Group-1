package com.epam.darafeyeu;

/**
 * Created by xdar on 23.2.15.
 */
public class Client {

    public static void main(String[] args) throws Exception {

        CamelServiceSpringDSL camelServiceJavaDSL =  new CamelServiceSpringDSL();

        camelServiceJavaDSL.send( "Hello" );
        String result = camelServiceJavaDSL.receive();

        System.out.println( result );

    }
}
