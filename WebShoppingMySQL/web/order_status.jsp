<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
            // fetch existing cart info
                String name = (String) session.getAttribute("name");
                Integer orderPrice = (Integer) request.getAttribute("orderPrice");
                Integer orderPoints = (Integer) request.getAttribute("orderPoints");
                List<ItemRecord> displayList = (List<ItemRecord>) request.getAttribute("displayList");
                Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cartMap");
                Map<Integer, ItemRecord> nameMap = (Map<Integer, ItemRecord>) session.getAttribute("nameMap");
                Map<Integer, Integer> displayMap = (Map<Integer, Integer>) request.getAttribute("displayMap");
                if(displayList == null || displayList.size() <= 0) // no item to checkout
                {
            %>
            <tr><td colspan="6">(No result is found)</td></tr>
            <%
                }
                else // iter items in cart to display
                {
                    for(Integer itemId : displayMap.keySet())
                    {
                        ItemRecord item = nameMap.get(itemId);
            %>
            <tr>
                <!--<td style="display:none"></td>-->
                <td><%=item.getDesc()%></td>
                <td><%=item.getBrand()%></td>
                <td><%=item.getPrice()%></td>
                <!-- count each item points seperately -->
                <td><%=item.getPoints() * displayMap.get(itemId)%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        </p>
        <p> Hello <%=name%> </p>
        <!-- print total points and price in all -->
        <p> Total points earned: <%=orderPoints%></p>
        <p> Total price you need to pay: <%=orderPrice%></p>
        <!--<button onclick="window.location.href='/checkout'">Check Out</button>-->
        <hr/>
        <a href="search.html">Do another search</a><br/>
        <a href="main.jsp">Go back to menu</a>
    </body>
</html>