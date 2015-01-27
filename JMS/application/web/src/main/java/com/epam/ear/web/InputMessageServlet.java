package com.epam.ear.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 23.01.15.
 */
@WebServlet(description = "Input Customer Servlet",
  urlPatterns = { "/InputMessageServlet", "/InputMessageServlet.do" })
public class InputMessageServlet extends HttpServlet {

    /**
     *
     * @param request - HttpServletRequest
     * @param response - HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */

   final protected void doGet(final HttpServletRequest request,
                              final  HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/jsps/input.jsp"
                ).forward(request, response);
    }
}
