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
        <div id="postJobsForm" class="jumbotron">
            <span id="closeForm">&times;</span>
            <h3><b>Post New Job</b></h3>
            <s:actionerror/>
            <s:form action="PostNewJob" method="post" id="postNewJobForm" validate="true">  
                <s:textfield name="companyname" key="companyname" label="Company Name" id="comapanyName" placeholder="Company Name"></s:textfield>
                <s:textfield name="post" key="post" label="Post" id="post" placeholder="Vacancy Post"></s:textfield>
                <s:textfield name="department" key="department" label="Department" id="Department" placeholder="Enter Department"></s:textfield>
                <s:textfield name="requiredExperience" label="Required Years of Experience" placeholder="0 - 50+ year(s)"></s:textfield>
                <s:textarea name="skills" key="skills" label="Skills Required" id="skillsTextArea" placeholder="Enter the skills required"></s:textarea>
                <s:checkbox name="vacancyStatus" fieldValue="true" label="Make Status as Open"/>
                <d:submit id="submitButton" value="submit" validate="true" ></d:submit>  
            </s:form>  
        </div>
        <div id="apppliedForJobs">

        </div>
    <div id="employerHomeBody">
        <h3>Hello Recruiter, <s:property value="email"/></h3>
        <div id="postedJobsCards">
        </div>
        <span><button name="postjob" id="postNewJobsButton" > Post New Job</button></div></span>
        <span><button name="applications" id="applicationsButton" onclick="viewApps();">View Applications</button></span>
        <!-- <s:form action="viewApplications" method="post" >
            <d:submit value="submit" id="viewApplicationsButton"></d:submit>
        </s:form> -->
    </div>
    </body>
</html>
