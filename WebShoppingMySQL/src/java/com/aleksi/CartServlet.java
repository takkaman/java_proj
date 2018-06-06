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

@WebServlet("/addtocart")
public class CartServlet extends HttpServlet
{
    @Resource(name="jdbc/dexin")
    private DataSource itemDB;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session =request.getSession();
        String itemId = request.getParameter("itemId");
        System.out.println(itemId);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //Statement statement = null;
        ResultSet resultset = null;
        ItemRecord itm = null;
//        List<ItemRecord> itemList = new ArrayList();
        try{
            connection = itemDB.getConnection();
            preparedStatement = connection.prepareStatement("select * from item i where i.itemId = ?");
            preparedStatement.setString(1, itemId);
//                preparedStatement.setString(2, password);
            resultset = preparedStatement.executeQuery();
            int count = 0;
            List<ItemRecord> cartList = (List<ItemRecord>) session.getAttribute("cartList");
            Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cartMap");
            Map<Integer, ItemRecord> nameMap = (Map<Integer,ItemRecord>) session.getAttribute("nameMap");
            if (cartList == null) {
//                System.out.println("EmptyCart");
                cartList = new ArrayList();
                cartMap = new HashMap();
                nameMap = new HashMap();
            }
            while(resultset.next())
            {
                itm = new ItemRecord();
                itm.setId(resultset.getInt("itemId"));
                itm.setDesc(resultset.getString("itemDescription"));
                itm.setBrand(resultset.getString("brand"));
                itm.setPrice(resultset.getInt("price"));
                itm.setPoints(resultset.getInt("points"));
                System.out.println(itm.getDesc());
                cartList.add(itm);
                if (cartMap.containsKey(itm.getId())) {
                    cartMap.put(itm.getId(), cartMap.get(itm.getId())+1);
                } else {
                    System.out.println("No exist");
                    cartMap.put(itm.getId(), 1);
                    System.out.println(itm.getDesc());
                    nameMap.put(itm.getId(), itm);
                }
            }
            
            session.setAttribute("cartList", cartList);
            session.setAttribute("cartMap", cartMap);
            session.setAttribute("nameMap", nameMap);
            RequestDispatcher rd = request.getRequestDispatcher("/cart_display.jsp");
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

