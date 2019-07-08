package jobsearch;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
public class PostedJobs  {

    public void getApplicationsCount() throws SQLException, ClassNotFoundException, IOException{
        JSONObject jsonAppliedCount=new JSONObject();
        String employerEmail = (String) ServletActionContext.getRequest().getSession().getValue("employerEmail");
        DatabaseClass dbobj = new DatabaseClass();
        ResultSet rs = dbobj.getPostedJobsCountByEmployer(employerEmail);
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
        HttpServletResponse response = ServletActionContext.getResponse();
        response.getWriter().print(jsonAppliedCount);
    }
    public void execute() throws SQLException, ClassNotFoundException, IOException{
        JSONObject json=new JSONObject();
        String employerEmail = (String) ServletActionContext.getRequest().getSession().getValue("employerEmail");;
        DatabaseClass dbobj = new DatabaseClass();
        ResultSet rs = dbobj.getPostedJobsByEmployer(employerEmail);
        ResultSet rs1 = dbobj.getEmployerProfileDetails(employerEmail);
        int i=0;
        while(rs.next()){
            json.put("postId"+i, rs.getString("postid"));
            json.put("companyName"+i, rs.getString("companyname"));
            json.put("post"+i, rs.getString("post"));
            json.put("skills"+i,rs.getString("skills"));
            json.put("requiredExperience"+i,rs.getString("requiredexperience"));
            json.put("salary"+i, rs.getString("salary"));
            json.put("vacancyStatus"+i,rs.getString("vacancyStatus"));
            json.put("location"+i, rs.getString("location"));
            json.put("postedBy"+i, rs.getString("postedby"));
            i++;
        }
        json.put("size", i);
        json.put("employerFullName", rs1.getString("fullname"));
        json.put("employerProfileEmail", rs1.getString("email"));
        json.put("employerCompanyName", rs1.getString("companyname"));
        json.put("employerLocation",rs1.getString("location"));
        json.put("employerMobileNumber", rs1.getString("mobilenumber"));
        HttpServletResponse response = ServletActionContext.getResponse();
        response.getWriter().print(json);
    }
}
