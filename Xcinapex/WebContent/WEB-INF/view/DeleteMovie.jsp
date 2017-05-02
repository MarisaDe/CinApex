<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Delete Movies</title>
 </head>
 <body>
 <jsp:include page="_menuEmp.jsp"></jsp:include>


    <h3 align = "center">Movie List</h3>
 <p style="color: red;">${errorString}</p>

 <form action="/Cinapex1/DeleteMovie">
    <table class="table table-striped" id = "movieTable">
       <tr>
       <th></th>
          <th>Id</th>
          <th>Name</th>
          <th>Type</th>
          <th>Rating</th>
          <th>DistrFee</th>
          <th>Copies</th>

       </tr>
       <c:forEach items="${MovieList}" var="movie" >
          <tr>
          	 <td> <button type = "submit" class="btn-danger">X</button></td>
          	 <td> <input type="hidden" name="MovieId2" value="${movie.id}"/>${movie.id}</td>
          	 <td>${movie.name}</td>
          	 <td>${movie.type}</td>
          	 <td>${movie.rating}</td>
          	 <td>${movie.distrFee}</td>
             <td>${movie.numCopies}</td>
          </tr>
       </c:forEach>
    </table>
    </form>
 </body>
</html>