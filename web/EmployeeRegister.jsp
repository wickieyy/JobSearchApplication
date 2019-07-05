<%-- 
    Document   : RegisterAsEmployee
    Created on : Jun 24, 2019, 12:18:14 PM
    Author     : test
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%@ taglib uri="/struts-dojo-tags" prefix="d"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Registration</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="scriptsource.js"></script>    
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet.css" />   
    </head>
    <body onload="loadPreferrences()">
        <div id="EmployeeRegisterForm" class="jumbotron">
            <h2>Register Page</h2>
            <p id="error" class="errorMessage1"></p>
            <s:actionerror/> 
            <s:form action="EmployeeRegister" method="post" id="registerForm" validate="true" enctype = "multipart/form-data">  
                <s:textfield name="fullName" key="fullName" label="Full Name" placeholder="Full Name"></s:textfield>
                <s:textfield name="email" key="email" label="Email" placeholder="Email"></s:textfield>
                <s:password name="password" key="password" placeholder="Password" label="Password"></s:password>
                <s:textfield name="locationRegister" key="location" placeholder="Location" label="Location"></s:textfield>
                <s:textfield name="pno" key="pno" placeholder="Mobile Number" label="Phone Number"></s:textfield>
                <s:checkboxlist name="colors" label="Preferences" list="colors" id="employeePreferences"></s:checkboxlist>
                <s:textfield name="OtherPreferences" id="OtherPreferences" placeholder="Separate Preferences by Comma" label="OtherPreferences"></s:textfield>
                <s:textfield name="experienceYears" placeholder="0 - 50+ years" label="Experience in Year(s)" ></s:textfield>
                <s:textfield name="experienceAsPost" placeholder="Experience As (optional)"label="Experience as "></s:textfield>
                <s:file label="Upload Resume (optional)" name="resumeUpload"></s:file>
                <d:submit id="submitButton" value="submit" validate="true" ></d:submit>  
                <s:property value="a"/><br/>  
            </s:form> 
            <!-- <form action = "upload" method = "post" enctype = "multipart/form-data">
                <label for = "myFile">Upload your file</label>
                <input type = "file" name = "myFile" />
                <input type = "submit" value = "Upload"/>
             </form> -->
        </div>
    </body>
</html>
