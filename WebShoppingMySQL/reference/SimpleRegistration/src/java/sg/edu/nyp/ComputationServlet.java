/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nyp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 160028S
 */
@WebServlet("/compute")
public class ComputationServlet extends HttpServlet {
    @Override
    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException
    {
      String height =request.getParameter("height");
      String weight =request.getParameter("height");
              
        int nHeight = Integer.parseInt(height);
        double dWeight = Double.parseDouble(weight);
        
        double bmi = (10000/(nHeight*nHeight)) * dWeight;
        
        request.setAttribute("bmi", bmi);
        
        RequestDispatcher rd = request.getRequestDispatcher("/add");
        rd.forward(request, response);
    }
    
}                                       
