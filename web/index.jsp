<%-- 
    Document   : index.jsp
    Created on : Jun 24, 2019, 11:54:42 AM
    Author     : test
--%>
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
        <title>Job Opportunities</title>
    </head>
    <body>
        <div id="indexPageDiv">
            <span>
                <a href="EmployerLogin.jsp" class="forEmployer">Employer Login</a>
            </span>
            <span>
                <a href="EmployeeLogin.jsp"  class="forEmployer">Employee Login</a>
            </span>
        </div>
    </body>
</html>
