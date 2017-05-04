<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Your Account</title>
 </head>
 <body>
 <jsp:include page="menu.jsp"></jsp:include>

<h3><center>Welcome back,  ${loggedInUser.firstName}!</center></h3>
<div class = "container">
<table class="table table-striped" id = "accTable">
  <tr>
    <th>Account- ${personType}</th>

  </tr>
  <tr>
    <td>Last Name: ${loggedInUser.lastName}</td>
    
  </tr>
  <tr>
    <td>First Name: ${loggedInUser.firstName}</td>
  
  </tr>
  <tr>
    <td>Address: ${loggedInUser.address } ${loggedInUser.zipcode}</td>
  
  </tr>
  <tr>
   
  </tr>
  <tr>
    <td>Telephone Number:${loggedInUser.telephone}</td>
    
  </tr>
  <tr>
    <td>Start Date: ${loggedInUser.startDate}</td> 
  </tr>
  
    <tr>
    <td>Hourly Rate: $${loggedInUser.hourlyRate}</td> 
  </tr>
</table>
</div>