<%-- 
    Document   : results
    Created on : May 30, 2018, 10:24:08 AM
    Author     : phyan
--%>
<%@page import="java.util.List"%>
<%--<%@page import="sg.edu.nyp.RegistrationRecord"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Main Page</title>
    </head>
    <body>
        <% String email=(String)request.getAttribute("email"); %>
        <p><% String username=(String)request.getAttribute("name"); %>
            Welcome! <%=username %>
        </p>
        <form name="user" action="profile" method="get">
            <input style="display:none" type="email" name="email" value="<%=email%>" required/><br/>
            <input type="submit" value="View Profile"/>
        </form>
    </body>
</html>
