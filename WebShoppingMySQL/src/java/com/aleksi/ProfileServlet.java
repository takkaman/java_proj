package com.aleksi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * view user profile
 * @author 
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet
{
    @Resource(name="jdbc/dexin")
    private DataSource itemDB;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // new user record instance and init related variables
        CustomerRecord userRcd = new CustomerRecord();
        System.out.println("profile!!");
        String email = request.getParameter("email");
        String username = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //Statement statement = null;
        ResultSet resultset = null;
        System.out.println(email);
        HttpSession session =request.getSession();
        try{
            // connect to database
            connection = itemDB.getConnection();
            // select user record via email
            preparedStatement = connection.prepareStatement("select * from customer c where c.email = ?");
            preparedStatement.setString(1, email);
            resultset = preparedStatement.executeQuery();
            
            //statement = connection.createStatement();
            //resultset = statement.executeQuery(sqlSearch);
            int count = 0;
            // althrough while, actually only one record returned
            while(resultset.next())
            {
                // set user related info for later display
                userRcd.setAddr1(resultset.getString("addressline1"));
                userRcd.setAddr2(resultset.getString("addressline2"));
                userRcd.setEmail(resultset.getString("email"));
                userRcd.setName(resultset.getString("fullname"));
                userRcd.setPostcode(resultset.getString("postalcode"));
                userRcd.setPhone(resultset.getString("mobile"));
                
                username = resultset.getString("fullname");
                System.out.println(username);
                count++;
            }

            // sanity check, if no user found return
            if (count == 0) { // 没有找到登录用户，返回登陆界面
                System.out.println("Not found user");
                response.sendRedirect(this.getServletContext().getContextPath());
            } else {
                // found user, send variable to web
                session.setAttribute("userRcd", userRcd);
                RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
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
            /*if(statement != null)
            {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
//            if(preparedStatement != null)
//            {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            if(connection != null)
//            {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }
}
