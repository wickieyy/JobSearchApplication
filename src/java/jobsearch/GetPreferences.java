/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

public class GetPreferences extends ActionSupport {
    private List<String> colors;
    
        public String getOtherPreferences() {
        return OtherPreferences;
    }

    public void setOtherPreferences(String OtherPreferences) {
        this.OtherPreferences = OtherPreferences;
    }
    

    
    private String fullName;
    private String email;
    private String password;
    private String location;
    private String pno;
    private int experienceYears;
    private String experienceAsPost;
    private String Department;
    private String OtherPreferences;

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

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getExperienceAsPost() {
        return experienceAsPost;
    }

    public void setExperienceAsPost(String experienceAsPost) {
        this.experienceAsPost = experienceAsPost;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
    public String checkList(){
        colors = new ArrayList<String>();
        try{
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            DatabaseClass dbobj = new DatabaseClass();
            ResultSet preferencesResultSet = dbobj.getPreferences();
            while(preferencesResultSet.next()){
                colors.add(preferencesResultSet.getString("designations"));             
            }
        }catch(Exception e){
            
        }
    return "none";
    }
    public String execute() throws ClassNotFoundException, SQLException{
//        System.out.print(colors.toString());
//        String designations="";
//        for(int i=0;i<colors.size();i++){
//            designations+=colors.get(i);
//            if(i<colors.size()-1){
//                designations+=",";
//            }
//        }
//        DatabaseClass database=new DatabaseClass();
//        database.employeeRegister(fullName, email, password,location, pno, experienceYears, experienceAsPost,designations);
//        if(OtherPreferences.equals("")){}
//        else{
//            database.insertPreferences(OtherPreferences);
//        }
        return "success";
    }
}
