/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobsearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
public class PostedJobs extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        JSONObject json=new JSONObject();
        JSONObject jsonAppliedJobs=new JSONObject();
        JSONObject jsonFinal = new JSONObject();
        EmployerLogin employerLogin = new EmployerLogin();
        String employerEmail = employerLogin.getEmail();
        DatabaseClass dbobj = new DatabaseClass();
        ResultSet rs = dbobj.getPostedJobsByEmployer(employerEmail);
        ResultSet rs1 = dbobj.getAppliedJobsForEmployer(employerEmail);
        int i=0;
        while(rs.next()){
            json.put("postedBy"+i,rs.getString("email"));
            json.put("postId"+i, rs.getString("postid"));
            json.put("companyName"+i, rs.getString("companyname"));
            json.put("post"+i, rs.getString("post"));
            json.put("skills"+i,rs.getString("skills"));
            json.put("requiredExperience"+i,rs.getString("requiredexperience"));
            json.put("department"+i, rs.getString("Department"));
            json.put("vacancyStatus"+i,rs.getString("vacancyStatus"));
            json.put("location"+i, rs.getString("location"));
            json.put("postedBy"+i, rs.getString("postedby"));
            i++;
        }
        json.put("size", i);
        i=0;
        while(rs1.next()){
            jsonAppliedJobs.put("postId"+i, rs1.getString("postid"));
            jsonAppliedJobs.put("companyName"+i, rs1.getString("companyname"));
            jsonAppliedJobs.put("post"+i, rs1.getString("post"));
            jsonAppliedJobs.put("applicantSkills"+i,rs1.getString("skills"));
            jsonAppliedJobs.put("applicantExperience"+i,rs1.getString("requiredexperience"));
            jsonAppliedJobs.put("department"+i, rs1.getString("Department"));
            jsonAppliedJobs.put("vacancyStatus"+i,rs1.getString("vacancyStatus"));
            jsonAppliedJobs.put("applicationStatus"+i,rs1.getString("applicationStatus"));
            jsonAppliedJobs.put("applicantEmail"+i,rs1.getString("email"));
            jsonAppliedJobs.put("currentLocation"+i, rs1.getString("current_location"));
            jsonAppliedJobs.put("postedBy"+i, rs1.getString("postedby"));
            i++;
        }
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
            Logger.getLogger(PostedJobs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostedJobs.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostedJobs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostedJobs.class.getName()).log(Level.SEVERE, null, ex);
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
