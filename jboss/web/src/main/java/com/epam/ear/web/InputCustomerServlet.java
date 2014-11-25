package com.epam.ear.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(description = "Input Customer Servlet", urlPatterns = {"/InputCustomerServlet", "/InputCustomerServlet.do"})
public class InputCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/jsps/input.jsp").forward(request, response);
    }

}
