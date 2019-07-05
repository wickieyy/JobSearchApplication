package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class EmployeeLogin extends ActionSupport implements SessionAware{
    private String email;
    private String password;
    private Map<String, Object> sessionMap;
    SessionMap<String,String> sessMap;

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

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
        HttpServletResponse response = ServletActionContext.getResponse();
        DatabaseClass database = new DatabaseClass();
        boolean bool=database.checkEmployeeLoginCredentials(email,password);
        if(bool == true){
            ServletActionContext.getRequest().getSession().putValue("employeeEmail", email);
            Cookie cookie = new Cookie("employeeEmail",email);  
            response.addCookie(cookie);
            return "success";
        }
        else{
            addActionError("Invalid Login Credentials");
            return "failure";
        }
    }
    public void setSession(Map map) {  
    sessMap=(SessionMap)map;  
    sessMap.put("login","true");  
    }  
    public String logout(){  
        sessMap.invalidate();  
        return "success";  
    }  
}
