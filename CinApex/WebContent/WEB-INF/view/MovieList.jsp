<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Cinapex</title>
 </head>
 <body>
 <jsp:include page="menu.jsp"></jsp:include>
 
    <h3 align = "center">All Available Movies List</h3>
 <p style="color: red;">${errorString}</p>
 <div class = "container">
    <table class = "table table-striped">
       <tr>
          <th>Movie Id</th>
          <th>Name</th>
          <th>Type</th>
          <th>Rating</th>
          <th>Distribution Fee ($)</th>
          <th>Copies Available</th>
       </tr>
       <c:forEach items="${MovieList}" var="movie" >
          <tr>
             <td>${movie.id}</td>
             <td>${movie.name}</td>
             <td>${movie.type}</td>
             <td>${movie.rating}</td>
             <td>${movie.distrFee}</td>
             <td>${movie.numCopies}</td>
             
          </tr>
       </c:forEach>
    </table>
 </div>
    
 </body>
</html>