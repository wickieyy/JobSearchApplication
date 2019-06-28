/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobsearch;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

@WebServlet(name = "PostedJobsForSeekers", urlPatterns = {"/PostedJobsForSeekers"})
public class PostedJobsForSeekers extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException, ClassNotFoundException {
        JSONObject json=new JSONObject();
        JSONObject jsonResponse=new JSONObject();
        EmployeeLogin employeeLogin = new EmployeeLogin();
        String employeeEmail=employeeLogin.getEmail();
        String email=(String) ServletActionContext.getRequest().getSession().getValue("employeeEmail");
        DatabaseClass dbobj = new DatabaseClass();
        ResultSet rs = dbobj.getPostedJobsByEmployee(email);
        ResultSet rs1 = dbobj.getUserDetails(email);
        int i=0;
        while(rs.next()){
            
            String preferedDesignations = rs.getString(1);
            String availableDesignation = rs.getString("post");
            if(preferedDesignations.contains(availableDesignation)){
                json.put("postId"+i, rs.getString("postid"));
                json.put("companyName"+i, rs.getString("companyname"));
                json.put("department"+i, rs.getString("post"));
                json.put("skills"+i,rs.getString("skills"));
                json.put("requiredExperience"+i,rs.getString("requiredexperience"));
                json.put("salary"+i, rs.getString("salary"));
                json.put("vacancyStatus"+i,rs.getString("vacancyStatus"));
                json.put("location"+i, rs.getString("location"));
                json.put("postedBy"+i, rs.getString("postedby"));
                json.put("applicationStatus"+i,rs.getString("application_status"));
                i++;
            }
        }
        json.put("size", i);
        json.put("userLocation",rs1.getString("location"));
        json.put("userMobileNumber",rs1.getString("mobilenumber"));
        json.put("userExperienceYears",rs1.getString("experience"));
        json.put("userPreferedDesignations",rs1.getString("department"));
        response.getWriter().print(json);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobsForSeekers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostedJobsForSeekers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PostedJobsForSeekers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostedJobsForSeekers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
