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
        <p><% String username=(String)request.getAttribute("username"); %>
            Welcome! <%=username %>
        </p>
    </body>
</html>
