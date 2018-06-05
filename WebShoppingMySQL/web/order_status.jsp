<%@page import="java.util.List"%>
<%@page import="com.aleksi.ItemRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping System - Order Status</title>
    </head>
<!--    <script>
        function addToCart(itemId) {
            window.location.href = "addtocart?itemId="+itemId;
        }
    </script>-->
    <body>
        <p>
            <b>Order:</b>
        </p>
        <hr/>
        <p>
            <!--A reminder note to the user on the search term he used-->    
        </p>
        <hr/>
        <p>
            <!--Results-->
        <table border="3">
            <tr><th>Desc</th><th>Brand</th><th>Price</th><th>Points</th></tr>
            
            <%
                Integer orderPrice = (Integer) request.getAttribute("orderPrice");
                Integer orderPoints = (Integer) request.getAttribute("orderPoints");
                List<ItemRecord> displayList = (List<ItemRecord>) request.getAttribute("displayList");
                if(displayList == null || displayList.size() <= 0)
                {
            %>
            <tr><td colspan="6">(No result is found)</td></tr>
            <%
                }
                else
                {
                    for(ItemRecord item : displayList)
                    {
            %>
            <tr>
                <!--<td style="display:none"></td>-->
                <td><%=item.getDesc()%></td>
                <td><%=item.getBrand()%></td>
                <td><%=item.getPrice()%></td>
                <td><%=item.getPoints()%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        </p>
        <p> Total points earned: <%=orderPoints%></p>
        <p> Total price you need to pay: <%=orderPrice%></p>
        <!--<button onclick="window.location.href='/checkout'">Check Out</button>-->
        <hr/>
        <a href="search.html">Do another search</a><br/>
        <a href="main.jsp">Go back to menu</a>
    </body>
</html>