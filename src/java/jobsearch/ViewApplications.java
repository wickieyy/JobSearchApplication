package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
public class ViewApplications extends ActionSupport {
  public void viewApps() throws ClassNotFoundException, SQLException, IOException{
      DatabaseClass dbobj = new DatabaseClass();
      String email = (String) ServletActionContext.getRequest().getSession().getValue("employerEmail");
      ResultSet rs1 = dbobj.getAppliedJobsForEmployer(email);
      JSONObject jsonAppliedJobs = new JSONObject();
      HttpServletResponse response = ServletActionContext.getResponse();
      int i=0;
      while(rs1.next()){
        jsonAppliedJobs.put("postId"+i,rs1.getString("postid"));
        jsonAppliedJobs.put("companyName"+i,rs1.getString("companyname"));
        jsonAppliedJobs.put("applicantEmail"+i,rs1.getString("email"));
        jsonAppliedJobs.put("applicantMobileNumber"+i, rs1.getString("current_mobilenumber"));
        jsonAppliedJobs.put("post"+i,rs1.getString("post"));
        jsonAppliedJobs.put("applicantSkills"+i,rs1.getString("applicant_skills"));
        jsonAppliedJobs.put("applicantExperience"+i, rs1.getString("applicant_experience"));
        jsonAppliedJobs.put("postedby"+i,rs1.getString("postedby"));
        jsonAppliedJobs.put("salary"+i,rs1.getString("salary"));
        jsonAppliedJobs.put("applicantName"+i, rs1.getString("fullname"));
        jsonAppliedJobs.put("applicantLocation"+i, rs1.getString("location"));
        i++;
      }
      jsonAppliedJobs.put("size",i);
      response.getWriter().print(jsonAppliedJobs);
//    return "success";
  }  
}
