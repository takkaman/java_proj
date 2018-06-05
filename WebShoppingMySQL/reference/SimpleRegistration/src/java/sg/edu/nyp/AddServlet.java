/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nyp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 160028S
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet{
  @Override
  protected void doGet (HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException
  {
       RegistrationRecord registrationRecord  = new RegistrationRecord();
       
       
       String name = request.getParameter("name");
       
       registrationRecord.setName(name);
       registrationRecord.setAdminno(request.getParameter("adminno"));
       registrationRecord.setEmail(request.getParameter("email"));
       registrationRecord.setGender(request.getParameter("gender"));
       registrationRecord.setSpecialisation(request.getParameter("specialisation"));
       
       String height = request.getParameter("height");
       int nHeught = Integer.parseInt(height);
       registrationRecord.setHeight(nHeught);
       
        registrationRecord.setWeight(Double.parseDouble(request.getParameter("weight")));
        registrationRecord.setBmi((Double)request.getAttribute("bmi"));
       
       HttpSession session =request.getSession();
       
       List<RegistrationRecord>reglist = (List<RegistrationRecord>) session.getAttribute("reglist");
       
       if (reglist == null){
       reglist = new ArrayList<>();
       }
       reglist.add(registrationRecord);
       
       session.setAttribute("reglist" , reglist);
       response.sendRedirect(this.getServletContext().getContextPath()+"/results.jsp");
 }
}
