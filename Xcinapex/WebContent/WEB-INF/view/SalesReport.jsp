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
 <jsp:include page="_menuEmp.jsp"></jsp:include>


<div class="container">
  <h2>Obtain a Sales Report For A Given Month</h2>

  <br>
  <form action="/Cinapex1/SalesReport_R">
    <div class="form-group row">
		  <label for="ex3">Date</label>
		  <div class="col-xs-4">
		    <input class="form-control" type="date" placeholder="2011-08-19" name="date">
		  </div>
		  </div>
      <br><br>
             <button class="btn btn-lg btn-primary btn-block" type="submit">Get Monthly Sales</button>
  </form>
</div>



 </body>
</html>