<!DOCTYPE html>
<html>
    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib prefix="s" uri="/struts-tags" %>
        <%@ taglib uri="/struts-dojo-tags" prefix="d"%> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Home Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="scriptsource.js"></script>    
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet.css" />
    </head>
    <body onload="loadPostedJobsForSeekers();" class="employeeHomeBodyContainer">
            <div class="sidenav">
                <span class="sideNavBar" onclick="showEmployeeHome();">Home</span>
                <span class="sideNavBar" onclick="showEmployeeProfile();">Profile</span>
                <span class="sideNavBar" onclick="showEmployeeLogout();">Log Out</span>
            </div>
        <div id="applyJobsForm" class="jumbotron">
            <span id="applyCloseForm">&times;</span>
            <h3><b>Apply Now</b></h3>
            <s:actionerror/>
            <s:form action="ApplyNewJob" method="post" id="applyNewJobForm" validate="true">  
                <s:textfield name="email" key="email" label="Email" id="applicantEmail" placeholder="Enter registered email"></s:textfield>
                <s:textfield name="currentMobileNumber" key="currentMobileNumber" label="Current Mobile Number" id="currentMobileNumber" placeholder="Mobile Number"></s:textfield>
                <s:textfield name="currentLocation" key="currentLocation" label="Current Location" id="currentLocation" placeholder="Current Location"></s:textfield>
                <s:textfield name="experience" label="Years of Experience" placeholder="0 - 50+ year(s)" id="experience"></s:textfield>
                <s:textarea name="skills" key="skills" label="Skills" id="skillsTextArea" placeholder="Enter the skills required"></s:textarea>
                <s:textfield name="postid"  id="applyPostId" hidden="true"/>
                <d:submit id="submitButton" value="submit" validate="true" ></d:submit>  
            </s:form>  
        </div>
        <div id="employeeHomeBody">
            <h3 id="employeeHomeBodyH3">Hello, <s:property value="email"/></h3>
            <div id="postedJobsCards">
            </div>
            <div id="userProfile" class="jumbotron showProfileBody">
                <h3><b>Profile</b></h3>
                <s:actionerror/>
                <s:form action="updateUserDetails" method="post" id="profileApplyNewJobForm" validate="true" changed="false">  
                    <s:textfield  name="email" key="email" label="Email" id="profileApplicantEmail" placeholder="Enter registered email"  readonly="true"></s:textfield>
                    <s:textfield  name="currentMobileNumber" key="currentMobileNumber" label="Current Mobile Number" id="ProfileCurrentMobileNumber" placeholder="Mobile Number" readonly="true"></s:textfield>
                    <s:textfield  name="currentLocation" key="currentLocation" label="Current Location" id="ProfileCurrentLocation" placeholder="Current Location" readonly="true"></s:textfield>
                    <s:textfield  name="experience" label="Years of Experience" placeholder="0 - 50+ year(s)" id="ProfileExperience" readonly="true"></s:textfield>
                    <s:textarea name="preferedDesignations" label="Prefered designations" id="profilePreferedDesignations" placeholder="seperate roles by ','" readonly="true"></s:textarea>
                    <d:submit id="submitButton" value="Update Changes" validate="true"></d:submit>  
                </s:form>  
                 <button id="editUserDetailsButton" onclick="enableEditing();">Edit Profile Details Here</button>
            </div>
        </div>
    </body>
</html>
