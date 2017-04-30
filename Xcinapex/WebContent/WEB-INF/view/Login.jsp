<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>All Movies</title>
 </head>
 <body>
 <jsp:include page="menu.jsp"></jsp:include>
 
  <h2 class="form-signin-heading">Log In</h2>
  
 <form class="form-signin" action="/Cinapex1/Account">
        <h3 class="form-signin-heading">Customer ID or SSN</h3>
        <label  for="inputEmail" class="sr-only">Customer ID / SSN</label>
        
        <div class='type'>
           <font color = "red">Category: </font>
             <select id="select" name="personType" style="width: 100px; height: 30px">
               <option>Customer</option>
               <option>Employee</option>
             </select>
        <div class="form-group">
	    use: 111-11-1111 for customer    
	        
        <input type="text" id="inputEmail" name="user" class="form-control" placeholder="Enter your SSN or Customer ID" required autofocus>
        
	        
	        
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
 </body>
</html>