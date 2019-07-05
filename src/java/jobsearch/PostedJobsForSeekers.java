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

public class PostedJobsForSeekers {
    public void getApplicationCount() throws ClassNotFoundException, SQLException, IOException{
        JSONObject jsonAppliedCount=new JSONObject();
        JSONObject jsonAlreadyApplied=new JSONObject();
        String employeeEmail = (String) ServletActionContext.getRequest().getSession().getValue("employeeEmail");;
        DatabaseClass dbobj = new DatabaseClass();
        ResultSet rs = dbobj.getPostedJobsCountByEmployee();
        ResultSet rs1 = dbobj.getAlreadyApplied(employeeEmail);
        int i=0;
        while(rs.next()){
            String xstr = (String) jsonAppliedCount.get(rs.getInt("postid"));
            if(xstr!=null){       
                int xint = (int) jsonAppliedCount.get(rs.getInt("postid"));
                jsonAppliedCount.put(rs.getInt("postid"),(xint+1) );
            }
            else{
                jsonAppliedCount.put(rs.getInt("postid"),1 );
            }
            i++;
        }
        jsonAppliedCount.put("size",i);
        i=0;
        while(rs1.next()){
            jsonAlreadyApplied.put("alreadyApplied"+i,rs1.getInt("postid"));
            i++;
        }
        jsonAppliedCount.put("alreadyApplied",jsonAlreadyApplied);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.getWriter().print(jsonAppliedCount);
    }
    public void execute() throws SQLException, IOException, ClassNotFoundException{
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
                json.put("applicationStatus"+i,"");
                i++;
            }
        }
        
        json.put("size", i);
        json.put("userLocation",rs1.getString("location"));
        json.put("userMobileNumber",rs1.getString("mobilenumber"));
        json.put("userExperienceYears",rs1.getString("experience"));
        json.put("userPreferedDesignations",rs1.getString("department"));
        HttpServletResponse response = ServletActionContext.getResponse();
        response.getWriter().print(json);
    }
}
