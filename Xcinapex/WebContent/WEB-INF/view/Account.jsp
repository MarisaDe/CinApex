<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>All Movies</title>
 </head>
 <body>
 <jsp:include page="menu.jsp"></jsp:include>
 
    <h3 align = "center">Your Account</h3>
 <p style="color: red;">${errorString}</p>
    <table border="1" cellpadding="5" cellspacing="1" align = "center">
      
       <h3>SSN:${loggedInUser.ssn }</h3>
         <h3>Name:${loggedInUser.FName }</h3>
           <h3>ID:${loggedInUser.id }</h3>
             <h3>Starting Date:${loggedInUser.startDate }</h3>
               <h3>Rate per Hour:${loggedInUser.hourlyRate }</h3>
    </table>
 
    
 </body>
</html>