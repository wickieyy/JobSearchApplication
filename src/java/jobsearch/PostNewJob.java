package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import org.apache.struts2.ServletActionContext;

public class PostNewJob extends ActionSupport{
    
    private String companyname;
    private String post;
    private int requiredExperience;
    private int salary;
    private boolean vacancyStatus;

    public boolean isVacancyStatus() {
        return vacancyStatus;
    }

    public void setVacancyStatus(boolean vacancyStatus) {
        this.vacancyStatus = vacancyStatus;
    }

    public int getDepartment() {
        return salary;
    }

    public void setDepartment(String department) {
        this.salary = salary;
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
        System.out.print("1235");
        System.out.print(companyname+post+skills);
        DatabaseClass dbobj = new DatabaseClass();
        EmployerRegister employerRegister = new EmployerRegister();
        String email=(String) ServletActionContext.getRequest().getSession().getValue("employerEmail");
        System.out.print(email+companyname+post+skills);
        dbobj.postNewJob(companyname,post,requiredExperience,skills,salary,vacancyStatus,email);
        addActionMessage("Job Posted");
        return "success";
    }
    
}
