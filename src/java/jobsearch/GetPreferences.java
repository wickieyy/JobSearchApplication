/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobsearch;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
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
    private String emailEmployeeRegister;
    private String password;
    private String locationRegister;
    private String pno;
    private int experienceYears;
    private String experienceAsPost;
    private String Department;
    private String OtherPreferences;
    private File resumeUpload;
    private String resumeUploadContentType;
    private String resumeUploadFileName;
    private String destPath;

    public String getLocationRegister() {
        return locationRegister;
    }

    public void setLocationRegister(String locationRegister) {
        this.locationRegister = locationRegister;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public File getResumeUpload() {
        return resumeUpload;
    }

        public String getEmailEmployeeRegister() {
        return emailEmployeeRegister;
    }

    public void setEmailEmployeeRegister(String emailEmployeeRegister) {
        this.emailEmployeeRegister = emailEmployeeRegister;
    }
    
    public void setResumeUpload(File resumeUpload) {
        this.resumeUpload = resumeUpload;
    }

    public String getResumeUploadContentType() {
        return resumeUploadContentType;
    }

    public void setResumeUploadContentType(String resumeUploadContentType) {
        this.resumeUploadContentType = resumeUploadContentType;
    }

    public String getResumeUploadFileName() {
        return resumeUploadFileName;
    }

    public void setResumeUploadFileName(String resumeUploadFileName) {
        this.resumeUploadFileName = resumeUploadFileName;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    private HttpServletRequest servletRequest;
    public String execute() throws ClassNotFoundException, SQLException{
        try {
           String destPath = "E:\\Resumes"; 
           File fileToCreate = new File(destPath,this.emailEmployeeRegister+".txt");  
           FileUtils.copyFile(this.resumeUpload, fileToCreate);//copying source file to new file  
        } catch(IOException e) {
           e.printStackTrace();
           addActionError(e.getMessage());
           return INPUT;
        }
        String designations="";
        for(int i=0;i<colors.size()-1;i++){
            designations+=colors.get(i)+",";
        }
        String[] otherPref = OtherPreferences.split(",");
        for(int i=0;i<otherPref.length;i++){
            designations+=otherPref[i]+",";
        }
        DatabaseClass database=new DatabaseClass();
        database.employeeRegister(fullName, emailEmployeeRegister, password,locationRegister, pno, experienceYears, experienceAsPost,designations);
        if(OtherPreferences.equals("")){}
        else{
            database.insertPreferences(OtherPreferences);
        }
        return "success";
    }
}
