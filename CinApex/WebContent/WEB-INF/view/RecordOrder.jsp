<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Record Order</title>
 </head>

 <body>
 <jsp:include page="menu.jsp"></jsp:include>


<div class="container">
  <h2>Record Movie Order</h2>
  <h4>Today's Date is: </h4>
	<p id="demo"></p>
	
	<script>
	var d = new Date();
	document.getElementById("demo").innerHTML = d.toDateString();
	</script>
  <br>
  <form action="/Cinapex1/OrderRecorded">
    <div class="form-group row">
     <div class="col-xs-4">
        <label for="ex3">Movie Order Id</label>
        <input class="form-control" type="number" name="MOId" placeholder="1" required autofocus>
      </div>
            <div class="col-xs-4">
        <label for="ex3">Account Id</label>
        <input class="form-control"  type="number" name="MOAId" placeholder="2" required autofocus>
      </div>
      <div class="col-xs-4">
        <label for="ex3">Movie Id</label>
        <input class="form-control"  type="number" name="MOMId" placeholder="3" required autofocus>
      </div>
      <br><br><br>
      <div class="col-xs-4">
        <label for="ex3">ReturnDate</label>
        <input class="form-control"  type="text" name="MORD" placeholder="2009-11-2" >
      </div>
      
      <br><br><br><br><br>
             <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
  </form>
</div>



 </body>
</html>