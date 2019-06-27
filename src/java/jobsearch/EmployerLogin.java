package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

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
        DatabaseClass database = new DatabaseClass();
        boolean bool=database.checkEmployerLoginCredentials(email,password);
        if(bool == true){
            return "success";
        }
        else{
            addActionError("Invalid Login Credentials");
            return "failure";
        }
    }
}
