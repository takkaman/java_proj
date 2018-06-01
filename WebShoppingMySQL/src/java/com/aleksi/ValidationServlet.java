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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String addr1 = request.getParameter("addr1");
        String addr2 = request.getParameter("addr2");
        String postCode = request.getParameter("postcode");
        String phone = request.getParameter("phone");
        String psw1 = request.getParameter("psw1");
        String psw2 = request.getParameter("psw2");
        EmailValidator emailValidator;
        emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(email)) {
//            response.sendRedirect(this.getServletContext().getContextPath());
            System.out.println("invalid email match");
            RequestDispatcher rd = request.getRequestDispatcher("/register.html");
            rd.forward(request, response);
        }
        else if(psw1.compareTo(psw2) != 0) {
            System.out.println("psw not match");
            RequestDispatcher rd = request.getRequestDispatcher("/register.html");
            rd.forward(request, response);
        }
        else if (!postCode.matches("[0-9]{6}")) {
            System.out.println("postcode not match");
            RequestDispatcher rd = request.getRequestDispatcher("/register.html");
            rd.forward(request, response);           
        }
        else if (!phone.matches("[0-9]{8}")) {
            System.out.println("phone not match");
            RequestDispatcher rd = request.getRequestDispatcher("/register.html");
            rd.forward(request, response);           
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
            rd.forward(request, response);
        }
    }
            
}
