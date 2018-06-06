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
import javax.servlet.ServletException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    @Resource(name="jdbc/dexin")
    private DataSource itemDB;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email + " " + password);
        String username = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //Statement statement = null;
        ResultSet resultset = null;
        System.out.println(email + " " + password);
        Map<Integer, Integer> cartMap = new HashMap();
        try{
            connection = itemDB.getConnection();
            preparedStatement = connection.prepareStatement("select * from customer c where c.email = ? and c.password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultset = preparedStatement.executeQuery();
            
            //statement = connection.createStatement();
            //resultset = statement.executeQuery(sqlSearch);
            int count = 0;
            while(resultset.next())
            {
                username = resultset.getString("fullname");
                System.out.println(username);
                count++;
            }
            if (count == 0) { // 没有找到登录用户，返回登陆界面
                System.out.println("Not found user");
                response.sendRedirect(this.getServletContext().getContextPath());
            } else {
                request.setAttribute("name", username);
                request.setAttribute("email", email);
                HttpSession session =request.getSession();
                session.setAttribute("email",email);
                session.setAttribute("name",username);
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
