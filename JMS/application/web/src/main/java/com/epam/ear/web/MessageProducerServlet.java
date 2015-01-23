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

    ConnectionFactory cf = null;
    Context ic = null;
    Connection connection = null;
    Session session = null;
    Topic topic = null;
    MessageProducer publisher = null;

    public void init(){
        System.out.println("!!!!!!!!!!!!INIT SERVLET!!!!!!!!!!!!!");

        try {
            ic = getContext();
            cf = (ConnectionFactory)ic.lookup("ConnectionFactory");
            topic = (Topic)ic.lookup("topic/test");
            connection = cf.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            publisher = session.createProducer(topic);
            connection.start();
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
            TextMessage message = session.createTextMessage(name);
            publisher.send(message);
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
