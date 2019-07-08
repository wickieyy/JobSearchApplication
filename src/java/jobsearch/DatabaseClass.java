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
    void postNewJob(String companyName, String post, int reqYearsExp, String skills,int salary,boolean vacancyStatus,String postedBy) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("insert into jobvacancies(companyname,post,skills,requiredexperience,salary,vacancyStatus,postedby) values(?,?,?,?,?,?,?)");
        ps.setString(1,companyName);
        ps.setString(2, post);
        ps.setString(3, skills);
        ps.setInt(4,reqYearsExp);
        ps.setInt(5,salary);
        if(vacancyStatus== true) ps.setString(6, "Open");
        else ps.setString(6,"Closed");
        ps.setString(7, postedBy);
        ps.executeUpdate();
    }

    ResultSet getPostedJobsByEmployer(String employerEmail) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select recruiters.location,jobvacancies.* from recruiters INNER JOIN jobvacancies on recruiters.companyname = jobvacancies.companyname where recruiters.email=?");
        ps.setString(1, employerEmail);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    ResultSet getPostedJobsByEmployee(String employeeEmail) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select a.department,b.*,c.location from users A INNER JOIN jobvacancies b INNER JOIN companies c on c.companyname=b.companyname LEFT JOIN applications d on d.postid = b.postid where a.email=?;");
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
        PreparedStatement ps = connection.prepareStatement("insert into applications(email,postid,current_location,current_mobilenumber,applicant_skills,applicant_experience,application_status) values(?,?,?,?,?,?,?);");
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
        PreparedStatement ps = connection.prepareStatement("select jobvacancies.*,applications.*,users.fullname,users.location from applications INNER JOIN jobvacancies on jobvacancies.postid = applications.postid INNER JOIN users on users.email=applications.email where jobvacancies.postedby=?;");
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

    ResultSet getPreferences() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select * from designations order by designation_id DESC;");
        ResultSet preferencesResultSet = ps.executeQuery();
        return preferencesResultSet;
    }

    void insertPreferences(String OtherPreferences) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("insert into designations(designations) values(?);");
        String[] otherPref = OtherPreferences.split(",");
        for(int i=0;i<otherPref.length;i++){
            ps.setString(1,otherPref[i].trim());
            ps.executeUpdate();
        }
    }

    void updateProfile(String email, String currentLocation, String currentMobileNumber, int experience, String preferedDesignations) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("update users set mobilenumber = ?, location=?, experience  =?, Department=? where email=?");
        ps.setString(1, currentMobileNumber);
        ps.setString(2, currentLocation);
        ps.setInt(3, experience);
        ps.setString(4, preferedDesignations);
        ps.setString(5, email);
        ps.executeUpdate();
    }
    ResultSet getPostedJobsCountByEmployer(String employerEmail) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select postid from applications;");
        ResultSet appliedCount = ps.executeQuery();
        return appliedCount;
    }

    ResultSet getPostedJobsCountByEmployee() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select postid from applications;");
        ResultSet appliedCount = ps.executeQuery();
        return appliedCount; 
    }

    ResultSet getAlreadyApplied(String employeeEmail) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select postid from applications where email=?;");
        ps.setString(1,employeeEmail);
        ResultSet alreadyApplied = ps.executeQuery();
        return alreadyApplied; 
    }

    ResultSet getEmployerProfileDetails(String employerEmail) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("select * from recruiters where email=?;");
        ps.setString(1,employerEmail);
        ResultSet employerProfile = ps.executeQuery();
        employerProfile.next();
        return employerProfile; 
    }

    void updateEmployerProfile(String employerProfileEmail, String employerProfileLocation, String employerProfileMobileNumber, String employerProfileFullName, String employerProfileCompanyName) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobsearch", "wickie", "wickie");
        PreparedStatement ps = connection.prepareStatement("update recruiters set mobilenumber = ?, location=?, fullname =?, companyname=? where email=?");
        ps.setString(1, employerProfileMobileNumber);
        ps.setString(2, employerProfileLocation);
        ps.setString(3, employerProfileFullName);
        ps.setString(4, employerProfileCompanyName);
        ps.setString(5, employerProfileEmail);
        ps.executeUpdate();
    }
}
