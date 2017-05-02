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
      
       <h3>${personType}</h3>
         <h3>Address :${loggedInUser.address } ${loggedInUser.zipcode}</h3>
         <h3>Telephone Number:${loggedInUser.telephone}</h3>
         <h3>Last Name:${loggedInUser.lastName}</h3>
         <h3>First Name:${loggedInUser.firstName}</h3>
         
         
    </table>
 
    
 </body>
</html>