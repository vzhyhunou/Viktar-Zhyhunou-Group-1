package com.epam.ear.web;

import com.epam.ear.customers.CustomerManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(description = "Add Customer Servlet", urlPatterns = {"/AddCompanyServlet", "/AddCompanyServlet.do"})
public class AddCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    CustomerManagerLocal companyManagerLocal;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        companyManagerLocal.createCustomer(name);
        request.getRequestDispatcher("/CustomerServlet.do").forward(request, response);
    }
}
