/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nyp.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


/**
 *
 * @author 160020C
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Resource(name="jdbc/jed")
    private DataSource dsBookCatalogue;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
            connection = dsBookCatalogue.getConnection();
            preparedStatement = connection.prepareStatement("select * from book where title like '%" + searchTerm + "%'");
            preparedStatement.setString(1, "%" + searchTerm + "%");
            resultset = preparedStatement.executeQuery();
            
            //statement = connection.createStatement();
            //resultset = statement.executeQuery(sqlSearch);
            
            while(resultset.next())
            {
                Book book = new Book();
                
                String isbn = resultset.getString("isbn");
                book.setIsbn(isbn);
                book.setTitle(resultset.getString("title"));
                book.setAuthor(resultset.getString("author"));
                book.setYear(resultset.getDate("year"));
                book.setPublisher(resultset.getString("publisher"));
                book.setAbout(resultset.getString("about"));
                
                searchResult.add(book);
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
                    Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            if(preparedStatement != null)
            {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null)
            {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("searchterm", searchTerm);
        session.setAttribute("searchresult", searchResult);
        
        response.sendRedirect(this.getServletContext().getContextPath() + "/searchresults.jsp");
    }

}
