<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>List Of Active Customers</title>
 </head>
 <body><jsp:include page="menu.jsp"></jsp:include>
 
    <h3 align = "center">List Of Active Customers</h3>
    <table border="1" cellpadding="5" cellspacing="1" align = "center" class="table table-striped">
       
        <tr>
          <th>Customer Id </th>
          <th>Name</th>
          <th>Rating</th>
          <th>Credit Card Number</th>
       </tr>
       <c:forEach items="${MovieList}" var="movie" >
          <tr>
             <td>${movie.custId}</td>
             <td>${movie.firstName} ${movie.lastName}</td>
             <td>${movie.rating}</td>
             <td>${movie.CCard}</td>
             
          </tr>
       </c:forEach>
    </table>
 
    
 </body>
</html>