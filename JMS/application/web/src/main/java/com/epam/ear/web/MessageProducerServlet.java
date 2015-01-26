package com.epam.ear.web;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by Alex on 23.01.15.
 */
@WebServlet(description = "MessageProducerServlet", urlPatterns = {"/JMSServlet", "/JMSServlet.do"})
public class MessageProducerServlet extends HttpServlet {

    TopicConnectionFactory topicConnectionFactory = null;
    Context initialContext = null;
    TopicConnection topicConnection = null;
    TopicSession topicSession = null;
    Topic topic = null;
    TopicPublisher topicPublisher = null;

    public void init(){
        System.out.println("!!!!!!!!!!!!INIT SERVLET!!!!!!!!!!!!!");

        try {
            initialContext = getContext();
            topicConnectionFactory = (TopicConnectionFactory)initialContext.lookup("ConnectionFactory");
            topic = (Topic)initialContext.lookup("topic/test");
            topicConnection = topicConnectionFactory.createTopicConnection();
            topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            topicPublisher = topicSession.createPublisher(topic);
            topicConnection.start();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!SENDING MESSAGE!!!!!!!!!!!!!!!!!!!!!!");
            String name = request.getParameter("name");
            TextMessage message = topicSession.createTextMessage(name);
            topicPublisher.publish(message);
        }
        catch (JMSException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("WEB-INF/jsps/input.jsp").forward(request, response);
    }

    private static Context getContext() throws NamingException {
        Hashtable<String, Object> p = new Hashtable<String, Object>();
        p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(p);
        return context;
    }
}
