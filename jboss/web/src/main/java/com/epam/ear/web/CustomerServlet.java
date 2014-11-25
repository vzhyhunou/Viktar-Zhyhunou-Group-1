package com.epam.ear.web;

import com.epam.ear.customers.Customer;
import com.epam.ear.customers.CustomerManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import java.util.Hashtable;


@WebServlet(description = "Customers Servlet", urlPatterns = {"/CustomerServlet", "/CustomerServlet.do"})
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    CustomerManagerLocal customerManagerLocal;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Getting companyManagerBean through initialContext. Doesn't work.
//        CompanyManagerLocal companyManager = null;
//        try {
//            companyManager = (CompanyManagerLocal) getContext()
//                    .lookup("java:global/myEar/myEjb-1.0-SNAPSHOT/CompanyManagerBean!com.epam.ear.companies.CompanyManager");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }

//      Instead use simple @EJB injection
        List<Customer> customers = customerManagerLocal.list();

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("WEB-INF/jsps/customers.jsp").forward(request, response);
    }


//    private static Context getContext() throws NamingException {
//        Hashtable<String, Object> p = new Hashtable<String, Object>();
//        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//        p.put("org.jboss.ejb.client.scoped.context", true);
//        p.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447/");
//        p.put(InitialContext.SECURITY_PRINCIPAL, "alex");
//        p.put(InitialContext.SECURITY_CREDENTIALS, "ttt");
//        p.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
//        final Context context = new InitialContext(p);
//        return context;
//    }
}
