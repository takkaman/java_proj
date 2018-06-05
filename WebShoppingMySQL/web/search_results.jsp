<%@page import="java.util.List"%>
<%@page import="com.aleksi.ItemRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping System - Search Results</title>
    </head>
    <script>
        function addToCart(itemId) {
            window.location.href = "order?itemId="+itemId;
        }
    </script>
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
        <table border="3">
            <tr><th>Desc</th><th>Brand</th><th>Price</th><th>Points</th><th>Action</th></tr>
            <%
                List<ItemRecord> itemList = (List<ItemRecord>) session.getAttribute("itemList");
                if(itemList == null || itemList.size() <= 0)
                {
            %>
            <tr><td colspan="6">(No result is found)</td></tr>
            <%
                }
                else
                {
                    for(ItemRecord item : itemList)
                    {
            %>
            <tr>
                <!--<td style="display:none"></td>-->
                <td><%=item.getDesc()%></td>
                <td><%=item.getBrand()%></td>
                <td><%=item.getPrice()%></td>
                <td><%=item.getPoints()%></td>
                <td><button onclick="addToCart(<%=item.getId()%>);">add to cart</button></td>
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