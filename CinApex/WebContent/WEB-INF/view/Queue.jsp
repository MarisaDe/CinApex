<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Movie Queue</title>
 </head>
 <body>
 <jsp:include page="menu.jsp"></jsp:include>
 
    <h3 align = "center">Queue</h3>
 <p style="color: red;">${errorString}</p>
    <table class="table table-striped">
      
       <h3>${sessionScope.personType} ${sessionScope.loggedInUser.firstName } ${sessionScope.loggedInUser.lastName}'s Queue</h3>
       
        <tr>
          <th>Movie Id</th>
          <th>Name</th>
          <th>Type</th>
          <th>Rating</th>
       </tr>
       <c:forEach items="${MovieList}" var="movie" >
          <tr>
             <td>${movie.id}</td>
             <td>${movie.name}</td>
             <td>${movie.type}</td>
             <td>${movie.rating}</td>
             
          </tr>
       </c:forEach>
    </table>
 
    
 </body>
</html>