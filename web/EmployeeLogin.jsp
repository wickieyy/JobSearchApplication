<%-- 
    Document   : EmployeeLogin
    Created on : Jun 24, 2019, 12:13:53 PM
    Author     : test
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-dojo-tags" prefix="d"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login As Employee</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="scriptsource.js"></script>    
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet.css" />    
    </head>
    <body onload="checkCookieForEmployee();">
        <div id="EmployeeLoginForm" class="jumbotron">
            <h3><b>Login</b></h3>
            <s:actionerror/>
            <s:form action="EmployeeLogin" method="post" validate="true" theme="xhtml">  
            <s:textfield name="email" key="email" label="Email" id="username"></s:textfield>
            <s:password name="password" key="password" label="password"></s:password>
            <d:submit id="submitButton" value="submit" validate="true" ></d:submit>
            </s:form>  
            <span id="employeeRegisterPage" onclick="getPreferences();">New User? Register as Employee</span>
        </div>
    </body>
</html>
