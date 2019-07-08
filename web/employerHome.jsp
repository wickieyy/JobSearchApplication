<%-- 
    Document   : employerHome
    Created on : Jun 24, 2019, 4:23:15 PM
    Author     : test
--%>

<!DOCTYPE html>
<html>
    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib prefix="s" uri="/struts-tags" %>
        <%@ taglib uri="/struts-dojo-tags" prefix="d"%> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employer Home Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="scriptsource.js"></script>    
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet.css" />
    </head>
    <body onload="loadPostedJobs();">
            <h3 id="employerHomeBodyH3">Hello Recruiter, <span id="employerUser"></h3>
            <s:if test="hasActionMessages()">
                <div class="alert alert-success alert-dismissible" id="actionMessageEmployerHome">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Success!</strong> <s:actionmessage/>
                </div>
            </s:if>
            <div class="sidenav">
                <span class="sideNavBar" id="employerHomePageNav" onclick="showEmployerHome();">Home</span>
                <span class="sideNavBar"  id="postNewJobsButton">Post New Job</span>
                <span class="sideNavBar"  id="employerProfileButton" onclick="showEmployerProfile();">Profile</span>
                <span class="sideNavBar" id="viewApps" onclick="viewApps();"  >View Applications</span>
                <span class="sideNavBar" onclick="showEmployerLogout();">Log Out</span>
            </div>
        <div id="postJobsForm" class="jumbotron">
            <span id="closeForm">&times;</span>
            <h3><b>Post New Job</b></h3>
            <s:actionerror/>
            <s:form action="PostNewJob" method="post" id="postNewJobForm" validate="true">  
                <s:textfield name="companyname" key="companyname" label="Company Name" id="comapanyName" placeholder="Company Name" readonly="true"></s:textfield>
                <s:textfield name="post" key="post" label="Post" id="post" placeholder="Vacancy Post"></s:textfield>
                <s:textfield name="salary" key="salary" label="salary" id="salary" placeholder="Enter Salary"></s:textfield>
                <s:textfield name="requiredExperience" label="Required Years of Experience" placeholder="0 - 50+ year(s)"></s:textfield>
                <s:textarea name="skills" key="skills" label="Skills Required" id="skillsTextArea" placeholder="Enter the skills required"></s:textarea>
                <s:checkbox name="vacancyStatus" checked="true" label="Make Status as Open"/>
                <d:submit id="EmployerProfileSubmitButton" value="submit" validate="true" ></d:submit>  
            </s:form>  
        </div>
        <div id="appliedForJobs">

        </div>
    <div id="employerHomeBody">
        <div id="postedJobsCards"></div>
    </div>
    <div id="employerProfile" class="jumbotron">
        <span><button id="editUserDetailsButton"  class="btn btn-info btn-sm" onclick="enableEditingForEmployerProfile();"><span class="glyphicon glyphicon-edit"></span>Edit Profile</button></span>
        <span id="employerProfileH3"><h3><b>Profile</b></h3>
        <br>
        <s:actionerror/>
        <s:form action="updateEmployerUserDetails" method="post" id="EmployerProfileJobForm" validate="true" changed="false">  
            <s:textfield  name="employerProfileEmail" key="email" label="Email" id="employerProfileEmail"   readonly="true"></s:textfield>
            <s:textfield  name="employerProfileFullName" key="employerProfileFullName" label="Full Name" id="employerProfileFullName" placeholder="Full Name" readonly="true"></s:textfield>
            <s:textfield  name="employerProfileCompanyName" key="employerProfileCompanyName" label="Company Name" id="employerProfileCompanyName" placeholder="Company Name" readonly="true"></s:textfield>
            <s:textfield  name="employerProfileMobileNumber" key="currentMobileNumber" label="Current Mobile Number" id="ProfileCurrentMobileNumber" placeholder="Mobile Number" readonly="true"></s:textfield>
            <s:textfield  name="employerProfileLocation" key="currentLocation" label="Current Location" id="ProfileCurrentLocation" placeholder="Current Location" readonly="true"></s:textfield>
            <d:submit id="submitButton" value="Update Changes" validate="true"></d:submit>
        </s:form>
    </div>
    </body>
</html>
