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
                <span class="sideNavBar" onclick="showEmployeeHome();" id="employerHomeNav">Home</span>
                <span class="sideNavBar" onclick="showEmployeeProfile();" id="employerProfileNav">Profile</span>
                <span class="sideNavBar" onclick="showEmployeeLogout();" id="employerLogOutNav">Log Out</span>
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
                <s:textarea name="skills" key="skills" label="Skills" id="skillsTextArea" placeholder="Enter the skills"></s:textarea>
                <s:textfield name="postid"  id="applyPostId" hidden="true"/>
                <d:submit id="submitButton" value="submit" validate="true" ></d:submit>  
            </s:form>  
        </div>
        <div id="employeeHomeBody">
            <h3 id="employeeHomeBodyH3">Hello, <span id="employeeUser"></span></h3>
            <s:if test="hasActionMessages()">
                    <div class="alert alert-success alert-dismissible" id="actionMessageEmployeeHome">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Success!</strong> <s:actionmessage/>
                    </div>
            </s:if>
            <div id="filterJobs">
                <span>
                    <h5><span id="selectTagSalary" class="badge badge-primary">
                        Select Salary <i class="downicon">&#x25BC;</i>
                    </span><span id="selectTagDesignations" class="badge badge-primary">
                            Select Designations <i class="downicon">&#x25BC;</i>
                    </span></h5>
                </span>
                <span>
                    <div>
                        <span id="salarySelect" >
                            <select id="filterSelectTag1" onchange="filterFunction();" multiple>
                                    <option value="none" selected>--All--</option>
                            </select>
                        </span>
                        <span id="designationsSelect" >
                            <select  id="filterSelectTag" onchange="filterFunction();" multiple >
                                <option value="none" selected>--All--</option>
                            </select>
                        </span>
                    </div>
                </span>
            </div>
                  
            <div id="postedJobsCards">
            </div>
            <div id="userProfile" class="jumbotron">
                </span><span><button id="editUserDetailsButton"  class="btn btn-info btn-sm" onclick="enableEditing();"><span class="glyphicon glyphicon-edit"></span>Edit Profile</button></span>
                <span id="userProfileH3"><h3><b>Profile</b></h3>
                <br>
                <s:actionerror/>
                <s:form action="updateUserDetails" method="post" id="profileApplyNewJobForm" validate="true" changed="false">  
                    <s:textfield  name="email" key="email" label="Email" id="profileApplicantEmail" placeholder="Enter registered email"  readonly="true"></s:textfield>
                    <s:textfield  name="currentMobileNumber" key="currentMobileNumber" label="Current Mobile Number" id="ProfileCurrentMobileNumber" placeholder="Mobile Number" readonly="true"></s:textfield>
                    <s:textfield  name="currentLocation" key="currentLocation" label="Current Location" id="ProfileCurrentLocation" placeholder="Current Location" readonly="true"></s:textfield>
                    <s:textfield  name="experience" label="Years of Experience" placeholder="0 - 50+ year(s)" id="ProfileExperience" readonly="true"></s:textfield>
                    <s:textarea name="preferedDesignations" label="Prefered designations" id="profilePreferedDesignations" placeholder="seperate roles by ','" readonly="true"></s:textarea>
                    <d:submit id="submitButton" value="Update Changes" validate="true"></d:submit>  
                </s:form>  
            </div>
        </div>
    </body>
</html>
