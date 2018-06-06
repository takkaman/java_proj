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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.validator.EmailValidator;

@WebServlet("/validate")
public class ValidationServlet extends HttpServlet
{
    @Resource(name="jdbc/dexin")
    private DataSource itemDB;
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //Statement statement = null;
        ResultSet resultset = null;
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
            try{
                connection = itemDB.getConnection();
                preparedStatement = connection.prepareStatement("select * from customer c where c.email = ?");
                preparedStatement.setString(1, email);
//                preparedStatement.setString(2, password);
                resultset = preparedStatement.executeQuery();
                int count = 0;
                while(resultset.next())
                {
                    count++;
                }
                if (count != 0) {
                    System.out.println("email duplicated");
                    RequestDispatcher rd = request.getRequestDispatcher("/register.html");
                    rd.forward(request, response);
                } else {
                    preparedStatement = connection.prepareStatement("insert into customer (fullname,email,addressline1,addressline2,postalcode,mobile,password) values (?,?,?,?,?,?,?)");
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, addr1);
                    preparedStatement.setString(4, addr2);
                    preparedStatement.setString(5, postCode);
                    preparedStatement.setString(6, phone);
                    preparedStatement.setString(7, psw1);
                    preparedStatement.executeUpdate();
                    System.out.println("DB updated");
                    request.setAttribute("name", name);
                    HttpSession session =request.getSession();
                    Map<Integer, Integer> cartMap = new HashMap();
                    session.setAttribute("email",email);
                    session.setAttribute("name",name);
                    session.setAttribute("cartMap", cartMap);
                    RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
                    rd.forward(request, response);
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
                System.err.println(ex.getMessage());
            }

            finally{
                if(resultset != null)
                {
                    try {
                        resultset.close();
                    } catch (SQLException ex) {
                        System.out.println("Err");
                    }
                }
            }
        }
    }
}
