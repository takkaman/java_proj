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
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/search")
public class SearchServlet extends HttpServlet
{
    @Resource(name="jdbc/dexin")
    private DataSource itemDB;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String desc = request.getParameter("desc");
        System.out.println(desc);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //Statement statement = null;
        ResultSet resultset = null;
        
        try{
            connection = itemDB.getConnection();
            preparedStatement = connection.prepareStatement("select * from item i where i.itemDescription like ?");
            preparedStatement.setString(1, '%'+desc+'%');
//                preparedStatement.setString(2, password);
            resultset = preparedStatement.executeQuery();
            int count = 0;
            while(resultset.next())
            {
                System.out.println(resultset.getString("itemDescription"));
                count++;
            }
//                request.setAttribute("name", name);
            RequestDispatcher rd = request.getRequestDispatcher("/search_result.jsp");
            rd.forward(request, response);

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

