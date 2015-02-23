package com.epam.darafeyeu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by xdar on 19.2.15.
 */
public class Server extends Thread {

    private static final int PORT = 9999;

    private ServerSocket serverSocket;

    public Server(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(30000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("Waiting for client on port " +  serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                String message = in.readUTF();
                System.out.println("Server read: " + message);

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + " Message read: " + message );
                server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String [] args)
    {

        try
        {
            Thread t = new Server(PORT);
            t.start();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
