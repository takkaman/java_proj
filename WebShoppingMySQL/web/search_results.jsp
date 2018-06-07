<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.aleksi.ItemRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping System - Search Results</title>
    </head>
    <script>
        // back to CartServlet to add specified item
        function addToCart(itemId) {
            window.location.href = "addtocart?itemId="+itemId;
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
            // fetch existing cart info, if specific product amount is already 5, later add to cart button will not backword to Servlet
                Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cartMap");
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
                <%

                if (cartMap.containsKey(item.getId()) && cartMap.get(item.getId()) == 5) // to-be added item already in existing cart and amount is 5, pop up failure message
                {
                    %>
                    
                    <td><button onclick="alert('Add fail! No more than 5 amount!');">add to cart</button></td>
                    <%
                } else { // item could be added into cart, pop up success message, back to servlet to proceed
                    %>
                    <td><button onclick="alert('Add success!');addToCart(<%=item.getId()%>);">add to cart</button></td>
                    <%
                }
                %>
                
            </tr>
            <%
                    }
                }
            %>
        </table>
        </p>
        <hr/>
        <a href="search.html">Do another search</a><br/>
        <a href="addtocart">View Cart</a><br/>
        <a href="main.jsp">Go back to menu</a>
    </body>
</html>