package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class EmployerLogin extends ActionSupport{
    private String email;
    private String password;

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
        boolean bool=database.checkEmployerLoginCredentials(email,password);
        if(bool == true){
            ServletActionContext.getRequest().getSession().putValue("employerEmail", email);
            Cookie cookie = new Cookie("employerEmail",email);                
            response.addCookie(cookie);
            return "success";
        }
        else{
            addActionError("Invalid Login Credentials");
            return "failure";
        }
    }
}
