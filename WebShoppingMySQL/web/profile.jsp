<%-- 
    Document   : profile record
    Created on : May 30, 2018, 10:24:08 AM
    Author     : 
--%>
<%@page import="java.util.List"%>
<%@page import="com.aleksi.CustomerRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal Information</title>
    </head>
    <body>
        <p>
            You personal information:
        </p>
        <table border="3">
            <tr>
                <th>Name</th><th>Email Address</th>
                <th>Addressline1</th><th>Addressline2</th><th>Postal Code</th>
                <th>Mobile</th>
            </tr>
            <!-- Insert your Scriptlets here -->
            <!-- fetch user record instance -->
            <% CustomerRecord userRcd = (CustomerRecord) session.getAttribute("userRcd");%>
                <tr>
                    <td><%=userRcd.getName()%></td>  
                    <td><%=userRcd.getEmail()%></td> 
                    <td><%=userRcd.getAddr1()%></td> 
                    <td><%=userRcd.getAddr2()%></td> 
                    <td><%=userRcd.getPostcode()%></td> 
                    <td><%=userRcd.getPhone()%></td> 
                </tr>
        </table>
    </body>
</html>
