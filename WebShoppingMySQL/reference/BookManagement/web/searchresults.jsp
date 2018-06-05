<%@page import="java.util.List"%>
<%@page import="sg.edu.nyp.library.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NYP Library Book Management System - Search Result</title>
    </head>
    <body>
        <p>
            <b>Search Results</b>
        </p>
        <hr/>
        <p>
            <!--A reminder note to the user on the search term he used-->    
        </p>
        <hr/>
        <p>
            <!--Results-->
        <table>
            <tr><th>ISBN</th><th>Title</th><th>Author</th><th>Year Published</th><th>Publisher</th><th>About</th></tr>
            <%
                List<Book> searchResult = (List<Book>) session.getAttribute("searchresult");
                if(searchResult == null || searchResult.size() <= 0)
                {
            %>
            <tr><td colspan="6">(No result is found)</td></tr>
            <%
                }
                else
                {
                    for(Book book : searchResult)
                    {
            %>
            <tr>
                <td><%=book.getIsbn()%></td>
                <td><%=book.getTitle()%></td>
                <td><%=book.getAuthor()%></td>
                <td><%=book.getYear()%></td>
                <td><%=book.getPublisher()%></td>
                <td><%=book.getAbout()%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        </p>
        <hr/>
        <a href="search.html">Do another search</a><br/>
        <a href="index.html">Go back to menu</a>
    </body>
</html>