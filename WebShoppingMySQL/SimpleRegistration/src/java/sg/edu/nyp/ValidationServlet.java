package sg.edu.nyp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.routines.EmailValidator;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@WebServlet("/validate")
/**
 *
 * @author 160028S
 */
public class ValidationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get e
        String email = request.getParameter("email");

        EmailValidator emailValidator;

        emailValidator = EmailValidator.getInstance();

        if (emailValidator.isValid(email)) {
            RequestDispatcher rd = request.getRequestDispatcher("/compute");
            rd.forward(request, response);
        } else {
            response.sendRedirect(this.getServletContext().getContextPath());
        }
    }
}
