package com.aleksi;


import java.awt.print.Book;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phyan
 */
// package sg.edu.nyp;


@WebServlet("/compute")
public class ComputationServlet extends HttpServlet
{
    @Resource(name="jdbc/dexin")
    private DataSource itemDB;
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {       
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //Statement statement = null;
        ResultSet resultset = null;
        
        List<Book> searchResult = new ArrayList();
        
        String searchTerm = request.getParameter("searchterm");
        String sqlSearch = "select * from book where title like '%" + searchTerm + "%'" ;
        
        try{
            connection = itemDB.getConnection();
            preparedStatement = connection.prepareStatement("select * from item i where i.itemId = ?");
            preparedStatement.setInt(1, 66);
            resultset = preparedStatement.executeQuery();
            
            //statement = connection.createStatement();
            //resultset = statement.executeQuery(sqlSearch);
            while(resultset.next())
            {
                System.out.println(resultset.getString("brand") + " " + resultset.getInt("price"));
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

        
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        
        int nHeight = Integer.parseInt(height);
        double dWeight = Double.parseDouble(weight);
        
        double bmi = (10000 / ((double) nHeight * (double) nHeight)) * dWeight;
        
        // request.setAttribute("bmi", bmi);
        RequestDispatcher rd = request.getRequestDispatcher("/results.jsp");
        rd.forward(request, response);
        System.out.println("AAA");
    }
}

