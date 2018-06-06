<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.aleksi.ItemRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping System - Add to Cart</title>
    </head>
    <script>
        function addToCart(itemId) {
            window.location.href = "addtocart?itemId="+itemId;
        }
    </script>
    <body>
        <p>
            <b>Cart:</b>
        </p>
<!--        <hr/>
        <p>
            A reminder note to the user on the search term he used    
        </p>
        <hr/>
        <p>-->
            <!--Results-->
        <table border="3">
            <tr><th>Desc</th><th>Brand</th><th>Price</th><th>Points</th><th>Amount</th></tr>
            <%
                Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cartMap");
                Map<Integer, ItemRecord> nameMap = (Map<Integer, ItemRecord>) session.getAttribute("nameMap");
                List<ItemRecord> cartList = (List<ItemRecord>) session.getAttribute("cartList");
                if(cartList == null || cartList.size() <= 0)
                {
            %>
            <tr><td colspan="6">(No result is found)</td></tr>        
        </table>

            <%
                }
                else
                {
                    for(Integer itemId : cartMap.keySet())
                    {
                        ItemRecord item = nameMap.get(itemId);
            %>
            <tr>
                <!--<td style="display:none"></td>-->
                <td><%=item.getDesc()%></td>
                <td><%=item.getBrand()%></td>
                <td><%=item.getPrice()%></td>
                <td><%=item.getPoints()%></td>
                <td><%=cartMap.get(itemId)%></td>
            </tr>

        
                    <%
                    }
                    %>
                            </table>
<button onclick="window.location.href='checkout'">Check Out</button></p>
<%
                }
            %>
        <hr/>
        
        <a href="search.html">Do another search</a><br/>
        <a href="main.jsp">Go back to menu</a>
    </body>
</html>