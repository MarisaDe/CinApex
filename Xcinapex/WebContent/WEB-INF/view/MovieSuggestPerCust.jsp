<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Movie Suggestion List for Customer</title>
 </head>
 <body>
 <jsp:include page="menu.jsp"></jsp:include>
 
    <h3 align = "center">Personalized Movie List</h3>
    
<div class="container">
   <div class="form-group row">
 <form action="/Cinapex1/ProducedMovieSugg">
 <div class="form-group row">
 <h2>Customer Id</h2>
     <div class="col-xs-4">
        <label for="ex3">Customer Id</label>
        <input class="form-control" id="ex3" type="number" name="cIdPersonal" placeholder="1" required autofocus>
      </div>
      <br>
<button type="submit" value="Submit" class="btn btn-primary">Produce</button>
</div>
 </form>
 
 
 <p style="color: red;">${errorString}</p>
    <table border="1" cellpadding="5" cellspacing="1" align = "center">
      
       <h3>${movie.id} Personalized List</h3>
       
        <tr>
          <th>Movie Id</th>
          <th>Name</th>
          <th>Type</th>
          <th>Rating</th>
       </tr>
       <c:forEach items="${MovieListPersonal}" var="movie" >
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