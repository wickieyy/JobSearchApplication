package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

public class ApplyNewJob extends ActionSupport{
    private String email;
    private String currentMobileNumber;
    private String currentLocation;
    private int experience;
    private String skills;
    private int postid;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }
    

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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
 public String execute() throws SQLException, ClassNotFoundException{
     System.out.println("jobsearch.ApplyNewJob.execute()");
     DatabaseClass dbobj = new DatabaseClass();
     dbobj.updateApplicants(email,currentLocation,currentMobileNumber,skills,experience,postid);
     addActionMessage("Application Submited");
     return "success";
 }

}
