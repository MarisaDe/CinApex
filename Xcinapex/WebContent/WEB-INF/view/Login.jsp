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
 
 <form class="form-signin" action="/Cinapex1/Account">
        <h3 class="form-signin-heading">Customer ID or SSN</h3>
          use: 111-11-1111
        <label  for="inputEmail" class="sr-only">Customer ID / SSN</label>
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