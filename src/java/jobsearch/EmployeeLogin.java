package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class EmployeeLogin extends ActionSupport implements SessionAware{
    private String email;
    private String password;
    private Map<String, Object> sessionMap;

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String execute() throws ClassNotFoundException, SQLException{
        DatabaseClass database = new DatabaseClass();
        boolean bool=database.checkEmployeeLoginCredentials(email,password);
        if(bool == true){
            ServletActionContext.getRequest().getSession().putValue("employeeEmail", email);
            return "success";
        }
        else{
            addActionError("Invalid Login Credentials");
            return "failure";
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = sessionMap;
    }
}
