package com.aleksi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phyan
 */
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.EmailValidator;

@WebServlet("/validate")
public class ValidationServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String email = request.getParameter("email");
        EmailValidator emailValidator;
        emailValidator = EmailValidator.getInstance();
        if(emailValidator.isValid(email)) {
            RequestDispatcher rd = request.getRequestDispatcher("/compute");
            rd.forward(request, response);
        } else {
//            System.out.println("NA");
            response.sendRedirect(this.getServletContext().getContextPath());
        }
    }
            
}
