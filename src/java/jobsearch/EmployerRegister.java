package jobsearch;

import java.sql.SQLException;

public class EmployerRegister {
    private String fullName;
    private String email;
    private String password;
    private String companyname;
    private String location;
    private String pno;
    private String companyLocation;

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }
    
    public String execute() throws ClassNotFoundException, SQLException{
        System.out.print("demo");
        DatabaseClass database=new DatabaseClass();
        database.insertCompany(companyname,companyLocation);
        database.employerRegister(fullName, email, password, companyname,location, pno);
        return "success";
    }
    public void validate(){
//        String regex = "^[A-Za-z0-9+_.-]+@[a-zA-Z0-9]+$";
//        if(getEmail().length()==0){
//           addFieldError("email","Email is required");
//        }
//        else if(!getEmail().matches(regex)){
//            addFieldError("email","Enter a valid Email Id");
//        }
//        else if(getPassword().length()==0){
//           addFieldError("password","Passsword is required");
//        }
//        else if(getPassword().length()>10){
//           addFieldError("password","Passsword length should be less than or equal to 10");
//        }
    }
}
