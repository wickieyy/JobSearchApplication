<%-- 
    Document   : RegisterAsEmployer
    Created on : Jun 24, 2019, 12:18:02 PM
    Author     : test
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%@ taglib uri="/struts-dojo-tags" prefix="d"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employer Registration</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="scriptsource.js"></script>    
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet.css" />    
    </head>
    <body>
        <div id="EmployerRegisterForm" class="jumbotron">
            <h3>Recruiter Registration</h3>
            <s:actionerror/> 
            <s:form action="EmployerRegister" method="post" id="registerForm" validate="true">  
                <s:textfield name="fullName" key="fullName" label="Full Name"></s:textfield>
                <s:textfield name="email" key="email" label="Email"></s:textfield>
                <s:password name="password" key="password" label="Password"></s:password>
                <s:textfield name="companyname" key="companyname" label="Company Name"></s:textfield>
                <s:textfield name="companyLocation" key="companyLocation" label="Company Location"></s:textfield>
                <s:textfield name="pno" key="pno"  label="Phone Number"></s:textfield>
                <s:textfield name="location" key="location" label="Location"></s:textfield>
                <d:submit id="submitButton" value="submit" validate="true" ></d:submit>  
            </s:form> 
        </div>
    </body>
</html>
