<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
  <title>Cinapex</title>
 </head>
 <body><jsp:include page="menu.jsp"></jsp:include>
 
    <h3 align = "center">Order History</h3>
 <p style="color: red;">${errorString}</p>
    <table border="1" cellpadding="5" cellspacing="1" align = "center">
      
       <h3>${sessionScope.personType} ${sessionScope.loggedInUser.firstName } ${sessionScope.loggedInUser.lastName}'s History</h3>
       
        <tr>
          <th>Movie Id</th>
          <th>Name</th>
          <th>Type</th>
          <th>Rating</th>
       </tr>
       <c:forEach items="${MovieList}" var="movie" >
       <form action="/Cinapex1/AddRating">
          <tr>   
         	 <td><input type="hidden" name="movieId" value="${movie.id}"/>${movie.id}</td>
             <td><input type="hidden" name="movieName" value="${movie.name}"/>${movie.name}</td>
             <td><input type="hidden" name="movieType" value="${movie.type}"/>${movie.type}</td>
             <td><input type="number" name="movieRating" value="${movie.rating}" onsubmit="refresh()" /></td>
             
          </tr>
         </form>
       </c:forEach>
    </table>
 
    
 </body>
 
 <script>
function refresh() {
    location.reload();
}
</script>
</html>