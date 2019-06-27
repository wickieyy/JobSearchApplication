package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
public class ViewApplications extends ActionSupport {
  public String execute() throws ClassNotFoundException, SQLException, IOException{
//      DatabaseClass dbobj = new DatabaseClass();
//      EmployerLogin e = new EmployerLogin();
//      System.out.print(e.getEmail());
//      ResultSet rs1 = dbobj.getAppliedJobsForEmployer("recruiter@com.in");
        JSONObject json = new JSONObject();
        json.put("key","value");
        HttpServletResponse response = ServletActionContext.getResponse();
        response.getWriter().print(json);
      return "success";
  }  
}
