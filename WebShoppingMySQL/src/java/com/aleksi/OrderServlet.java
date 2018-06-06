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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

@WebServlet("/checkout")
public class OrderServlet extends HttpServlet
{
    @Resource(name="jdbc/dexin")
    private DataSource itemDB;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session =request.getSession();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //Statement statement = null;
        ResultSet resultset = null;
        List<ItemRecord> cartList = (List<ItemRecord>) session.getAttribute("cartList");
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cartMap");
        Map<Integer, ItemRecord> nameMap = (Map<Integer,ItemRecord>) session.getAttribute("nameMap");
        List<ItemRecord> displayList = new ArrayList();
        Map<Integer, Integer> displayMap = new HashMap();
        Integer customerId = 0;
        Integer orderPrice = 0;
        Integer orderPoints = 0;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String email = (String) session.getAttribute("email");
        try{
            connection = itemDB.getConnection();
            preparedStatement = connection.prepareStatement("select * from customer c where c.email = ?");
            preparedStatement.setString(1, email);
            resultset = preparedStatement.executeQuery();
            while(resultset.next())
            {
                customerId = resultset.getInt("customerId");
            }
            for (ItemRecord item: cartList) {
                orderPrice += item.getPrice();
                orderPoints += item.getPoints();
                displayList.add(item);
                if (displayMap.containsKey(item.getId())) {
                    displayMap.put(item.getId(), displayMap.get(item.getId())+1);
                } else {
                    displayMap.put(item.getId(), 1);
                }
            }
            preparedStatement = connection.prepareStatement("insert into orders (customerid,orderprice,orderpoints,timestamp) values (?,?,?,?)");
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, orderPrice);
            preparedStatement.setInt(3, orderPoints);
            preparedStatement.setTimestamp(4, time);
            preparedStatement.executeUpdate();

            request.setAttribute("orderPrice", orderPrice);
            request.setAttribute("orderPoints", orderPoints);
            request.setAttribute("displayList", displayList);
            request.setAttribute("displayMap", displayMap);
            cartList = new ArrayList();
            session.setAttribute("cartList", cartList);
            RequestDispatcher rd = request.getRequestDispatcher("/order_status.jsp");
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

