/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobsearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseClass {
    void employeeRegister(String fullName, String email, String password, String location, String pno,int experienceYears,String experienceAsPost,String department) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("insert into users values(?,?,?,?,?,?,?,?)");
        ps.setString(1,fullName);
        ps.setString(2,email);
        ps.setString(3,password);
        ps.setString(4,location);
        ps.setString(5,pno);
        ps.setInt(6,experienceYears);
        ps.setString(7, experienceAsPost);
        ps.setString(8,department);
        ps.executeUpdate();
    }
    void employerRegister(String fullName, String email, String password, String companyname, String location, String pno) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("insert into recruiters values(?,?,?,?,?,?)");
        ps.setString(1,fullName);
        ps.setString(2,email);
        ps.setString(3,password);
        ps.setString(4,companyname);
        ps.setString(5,location);
        ps.setString(6, pno);
        ps.executeUpdate();
    }

    void insertCompany(String companyname,String location) throws ClassNotFoundException, SQLException {
       Class.forName("com.mysql.jdbc.Driver");
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
       PreparedStatement ps = connection.prepareStatement("insert into companies values(?,?)");
       ps.setString(1,companyname);
       ps.setString(2, location);
       ps.executeUpdate();
    }

    boolean checkEmployerLoginCredentials(String id, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select * from recruiters where Email=?");
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
           if(rs.getString("password").equals(password)){
              return true;
           }
           return false;
        }
        return false;
    }
    boolean checkEmployeeLoginCredentials(String id, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select * from users where email=?");
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
           if(rs.getString("password").equals(password)){
              return true;
           }
           return false;
        }
        return false;
    }
    void postNewJob(String companyName, String post, int reqYearsExp, String skills,String department,boolean vacancyStatus,String postedBy) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("insert into jobvacancies(companyname,post,skills,requiredexperience,department,vacancyStatus) values(?,?,?,?,?,?)");
        ps.setString(1,companyName);
        ps.setString(2, post);
        ps.setString(3, skills);
        ps.setInt(4,reqYearsExp);
        ps.setString(5,department);
        if(vacancyStatus== true) ps.setString(6, "Open");
        else ps.setString(6,"Closed");
        ps.setString(7, postedBy);
        ps.executeUpdate();
    }

    ResultSet getPostedJobsByEmployer(String employerEmail) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select recruiters.email,recruiters.location,jobvacancies.* from recruiters INNER JOIN jobvacancies on recruiters.companyname = jobvacancies.companyname;");
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    ResultSet getPostedJobsByEmployee(String employeeEmail) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select b.*,c.location,d.application_status from users A INNER JOIN jobvacancies b on a.Department = b.Department INNER JOIN companies c on c.companyname=b.companyname LEFT JOIN applications d on d.postid = b.postid where a.email=?;");
        ps.setString(1,employeeEmail);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    void updatePostedJobStatus(int postId,String status) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("update jobvacancies set vacancyStatus=? where postid=?");
        ps.setString(1,status);
        ps.setInt(2,postId);
        ps.executeUpdate();
    }

    void updateApplicants(String email, String currentLocation, String currentMobileNumber, String skills, int experience, int postid) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("insert into applications(email,postid,current_location,current_mobilenumber,applicant_skills,applicant_experience) values(?,?,?,?,?,?.?);");
        ps.setString(1,email);
        ps.setInt(2,postid);
        ps.setString(3, currentLocation);
        ps.setString(4, currentMobileNumber);
        ps.setString(5, skills);
        ps.setInt(6, experience);
        ps.setString(7,"Applied");
        ps.executeUpdate();
    }

    ResultSet getAppliedJobsForEmployer(String employerEmail) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select jobvacancies.*,applications.* from applications INNER JOIN jobvacancies on jobvacancies.postid = applications.postid where jobvacancies.postedby=?;");
        ps.setString(1,employerEmail);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    ResultSet getUserDetails(String email) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select * from users where email=?");
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs;
    }
}