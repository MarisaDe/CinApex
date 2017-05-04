<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Edit Delete Movie</title>
 </head>
 <body>
<jsp:include page="menu.jsp"></jsp:include>


    <h3 align = "center">Movie List</h3>
 <p style="color: red;">${errorString}</p>

 <form action="/Cinapex1/DeleteMovie">
 <div class="form-group row">
 <h2>Delete Movie</h2>
     <div class="col-xs-4">
        <label for="ex3">Movie Id</label>
        <input class="form-control" id="ex3" type="number" name="MovieId2" placeholder="1" required autofocus>
      </div>
      <br>
<input type="submit" value="Submit">
</div>
 </form>

 <form action="/Cinapex1/EditMovie">
 <div class="form-group row">
 <h2>Edit Movie</h2>
     <div class="col-xs-4">
        <label for="ex3">Id</label>
        <input class="form-control" id="ex3" type="number" name="MovieId" placeholder="1" required autofocus>
      </div>
         <div class="col-xs-4">
        <label for="ex3">Name</label>
        <input class="form-control" id="ex3" type="text" name="MovieName" placeholder="Departed" >
      </div>
         <div class="col-xs-4">
        <label for="ex3">Type</label>
        <input class="form-control" id="ex3" type="text" name="MovieType" placeholder="Drama" >
      </div>
         <div class="col-xs-4">
        <label for="ex3">Rating</label>
        <input class="form-control" id="ex3" type="number" name="MovieRating" placeholder="2" >
      </div>
         <div class="col-xs-4">
        <label for="ex3">DistFee</label>
        <input class="form-control" id="ex3" type="number" name="MovieDistFee" placeholder="4000" >
      </div>
         <div class="col-xs-4">
        <label for="ex3">NumCopies</label>
        <input class="form-control" id="ex3" type="number" name="MovieNumCopies" placeholder="5">
      </div>
      <br>
<input type="submit" value="Submit">
</div>
 </form>
 
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
          	 <td>${movie.id}</td>
          	 <td>${movie.name}</td>
          	 <td>${movie.type}</td>
          	 <td>${movie.rating}</td>
          	 <td>${movie.distrFee}</td>
             <td>${movie.numCopies}</td>
          </tr>
       </c:forEach>
    </table>  
 </body>
</html>