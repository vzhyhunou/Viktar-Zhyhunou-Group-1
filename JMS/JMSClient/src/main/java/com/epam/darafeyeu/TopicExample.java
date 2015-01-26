package com.epam.darafeyeu;

import java.util.Hashtable;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class TopicExample implements MessageListener
{
    public void example() throws Exception
    {
        Context initialContext = null;
        TopicConnectionFactory topicConnectionFactory;
        TopicConnection topicConnection =  null;

        try
        {
            initialContext = getContext();

            topicConnectionFactory = (TopicConnectionFactory)initialContext.lookup("jms/RemoteConnectionFactory");
            Topic topic = (Topic)initialContext.lookup("jms/topic/test");

            topicConnection = topicConnectionFactory.createTopicConnection();
            TopicSession topicSession = topicConnection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
            topicSession.createSubscriber(topic).setMessageListener(this);

            topicConnection.start();

            System.out.print("Waiting for messages. Type Enter to exit\n");
            System.in.read();
        }
        finally
        {
            if (initialContext != null)
            {
                try
                {
                    initialContext.close();
                }
                catch(Exception e)
                {
                    throw e;
                }
            }
            closeConnection(topicConnection);
        }
    }
    public synchronized void onMessage(Message message)
    {
        TextMessage text = (TextMessage)message;
        String strMessage = null;
        try {
            strMessage = text.getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.out.println("Message received: "+strMessage);
    }

    private void closeConnection(Connection con)
    {
        try
        {
            if (con != null)
            {
                con.close();
            }
        }
        catch(JMSException jmse)
        {
            System.out.println("Could not close connection " + con +" exception was " + jmse);
        }
    }

    public static void main(String[] args) throws Exception
    {
        new TopicExample().example();
    }

    private static Context getContext() throws NamingException {
        Hashtable<String, Object> p = new Hashtable<String, Object>();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        p.put("org.jboss.ejb.client.scoped.context", true);
        p.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447");
        p.put(InitialContext.SECURITY_PRINCIPAL, "alex");
        p.put(InitialContext.SECURITY_CREDENTIALS, "ttt");
        p.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        p.put("jboss.naming.client.ejb.context", true);

        final Context context = new InitialContext(p);
        return context;
    }
}