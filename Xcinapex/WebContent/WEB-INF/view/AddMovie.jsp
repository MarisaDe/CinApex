<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Add Movie</title>
 </head>
 <body>
 <jsp:include page="_menuEmp.jsp"></jsp:include>


<div class="container">
  <h2>Add an Movie</h2>
  <p>Fill out all fields below to add an Movie to the database:</p>
  <form action="/Cinapex1/MovieAdded">
    <div class="form-group row">
     <div class="col-xs-4">
        <label for="ex3">Movie Id</label>
        <input class="form-control" id="ex3" type="number" name="MovieId" placeholder="1" required autofocus>
      </div>
            <div class="col-xs-4">
        <label for="ex3">Movie Name</label>
        <input class="form-control" id="ex3" type="text" name="MovieName" placeholder="The Departed" required autofocus>
      </div>
      <div class="col-xs-4">
        <label for="ex3">Type</label>
        <input class="form-control" id="ex3" type="text" name="MovieType" placeholder="Drama" required autofocus>
      </div>
      <div class="col-xs-4">
        <label for="ex3">Rating</label>
        <input class="form-control" id="ex3" type="number" name="MovieRating" placeholder="5" required autofocus>
      </div>
      <br><br><br>
      <div class="col-xs-4">
        <label for="ex3">DistrFee</label>
        <input class="form-control" id="ex3" type="number" name="MovieDistrFee" placeholder="5000" required autofocus>
      </div>
       <div class="col-xs-4">
        <label for="ex3">NumCopies</label>
        <input class="form-control" id="ex3" type="number" name="MovieCopies" placeholder="4" required autofocus>
      </div>
      
      <br><br>
             <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
  </form>
</div>



 </body>
</html>