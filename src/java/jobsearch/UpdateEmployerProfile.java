package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class UpdateEmployerProfile extends ActionSupport
{
    private String employerProfileFullName,employerProfileCompanyName,employerProfileEmail,employerProfileMobileNumber,employerProfileLocation;

    public String getEmployerProfileFullName() {
        return employerProfileFullName;
    }

    public void setEmployerProfileFullName(String employerProfileFullName) {
        this.employerProfileFullName = employerProfileFullName;
    }

    public String getEmployerProfileCompanyName() {
        return employerProfileCompanyName;
    }

    public void setEmployerProfileCompanyName(String employerProfileCompanyName) {
        this.employerProfileCompanyName = employerProfileCompanyName;
    }

    public String getEmployerProfileEmail() {
        return employerProfileEmail;
    }

    public void setEmployerProfileEmail(String employerProfileEmail) {
        this.employerProfileEmail = employerProfileEmail;
    }

    public String getEmployerProfileMobileNumber() {
        return employerProfileMobileNumber;
    }

    public void setEmployerProfileMobileNumber(String employerProfileMobileNumber) {
        this.employerProfileMobileNumber = employerProfileMobileNumber;
    }

    public String getEmployerProfileLocation() {
        return employerProfileLocation;
    }

    public void setEmployerProfileLocation(String employerProfileLocation) {
        this.employerProfileLocation = employerProfileLocation;
    }
    
    public String execute() throws ClassNotFoundException, SQLException{
        DatabaseClass dbobj = new DatabaseClass();
        dbobj.updateEmployerProfile(employerProfileEmail,employerProfileLocation,employerProfileMobileNumber,employerProfileFullName,employerProfileCompanyName);
        addActionMessage("Profile Updated !!");
        return "success";
    }
}
