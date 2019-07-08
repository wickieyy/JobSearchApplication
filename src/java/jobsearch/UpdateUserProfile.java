package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.omg.PortableServer.Servant;

public class UpdateUserProfile extends ActionSupport{
    private String email;
    private String currentMobileNumber;
    private String currentLocation;
    private int experience;
    private String preferedDesignations;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentMobileNumber() {
        return currentMobileNumber;
    }

    public void setCurrentMobileNumber(String currentMobileNumber) {
        this.currentMobileNumber = currentMobileNumber;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPreferedDesignations() {
        return preferedDesignations;
    }

    public void setPreferedDesignations(String preferedDesignations) {
        this.preferedDesignations = preferedDesignations;
    }
    
    public String updateUserDetails() throws ClassNotFoundException, SQLException, IOException, ServletException{
        DatabaseClass dbobj = new DatabaseClass();
        dbobj.updateProfile(email,currentLocation,currentMobileNumber,experience,preferedDesignations);
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/plain,charset=utf-8");
        RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
        rd.include(request,response);
        addActionMessage("Profile Updated !!");
        return "success";
    }
}
