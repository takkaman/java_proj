<%@page import="java.util.List"%>
<%@page import="sg.edu.nyp.RegistrationRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Registration</title>
    </head>
    <body>
        <p>
            You have added the following information:
        </p>
        <table>
            <tr>
                <th>Name</th><th>Admin Number</th><th>Email Address</th>
                <th>Gender</th><th>Choice of Specialisation</th>
                <th>Height (in cm)</th><th>Weight (in kg)</th><th>BMI</th>
            </tr>
            <!-- Insert your Scriptlets here -->
            <%
		    List<RegistrationRecord> reglist =  
                          (List<RegistrationRecord>) 
                            session.getAttribute("reglist");     
                          
                          for(RegistrationRecord registrationRecord:reglist)
{
		%>
                <tr>
                    <td>><%=registrationRecord.getName()%></td>  
                    <td>><%=registrationRecord.getAdminno()%></td> 
                    <td>><%=registrationRecord.getEmail()%></td> 
                    <td>><%=registrationRecord.getGender()%></td> 
                    <td>><%=registrationRecord.getSpecialisation()%></td> 
                    <td>><%=registrationRecord.getHeight()%></td> 
                    <td>><%=registrationRecord.getWeight()%></td> 
                    <td>><%=registrationRecord.getBmi()%></td> 
                </tr>
                <%
}
                          %>
        </table>
        <a href="index.html">Add another record?</a>
        <form action="logout" method="post">
        <input type="submit" value="Log Out"/>
    </form>
    </body>
</html>