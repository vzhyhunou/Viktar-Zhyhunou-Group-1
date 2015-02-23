package com.epam.darafeyeu;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by xdar on 23.2.15.
 */

public class SocketSender implements Processor {

    private static final String HOST_NAME = "localhost";
    private static final int PORT = 9999;

    @Override
    public void process(Exchange exchange) throws Exception {

        Socket client = new Socket( HOST_NAME, PORT );
        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        String body = exchange.getIn().getBody( String.class );
        out.writeUTF(body);

        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream( inFromServer );
        System.out.println("Response from server: " + in.readUTF());
        exchange.getOut().setBody("!!!!!!!");

        client.close();

    }
}
