package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

public class PostNewJob extends ActionSupport{
    
    private String companyname;
    private String post;
    private int requiredExperience;
    private String department;
    private boolean vacancyStatus;

    public boolean isVacancyStatus() {
        return vacancyStatus;
    }

    public void setVacancyStatus(boolean vacancyStatus) {
        this.vacancyStatus = vacancyStatus;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getRequiredExperience() {
        return requiredExperience;
    }

    public void setRequiredExperience(int requiredExperience) {
        this.requiredExperience = requiredExperience;
    }
    private String skills;

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public String execute() throws ClassNotFoundException, SQLException{
        DatabaseClass dbobj = new DatabaseClass();
        EmployerRegister employerRegister = new EmployerRegister();
        dbobj.postNewJob(companyname,post,requiredExperience,skills,department,vacancyStatus,employerRegister.getEmail());
        return "success";
    }
    
}
