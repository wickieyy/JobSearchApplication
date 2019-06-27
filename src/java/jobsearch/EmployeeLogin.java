package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

public class EmployeeLogin extends ActionSupport {
    private String email;
    private String password;

    public String getEmail() {
        System.out.print(email+"get Method");
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
        System.out.println("in excec"+email);
        DatabaseClass database = new DatabaseClass();
        boolean bool=database.checkEmployeeLoginCredentials(email,password);
        setEmail(email);
        if(bool == true){
            return "success";
        }
        else{
            addActionError("Invalid Login Credentials");
            return "failure";
        }
    }
}
