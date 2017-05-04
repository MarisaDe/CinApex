<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
<<<<<<< HEAD
    <title>Login</title>
=======
<title>Cinapex</title>
>>>>>>> 9995afd9e3a50e9eb2e6a9b7ea7625856a04fc62
 </head>
 <body>
 <jsp:include page="menu.jsp"></jsp:include>
 
 <style>
 @import url(http://fonts.googleapis.com/css?family=Open+Sans:400,700);
body {background: #262626;}
.login { width: 400px;margin: 16px auto;font-size: 16px;}
/* Reset top and bottom margins from certain elements */
.login-header,.login p { margin-top: 0;margin-bottom: 0;}
/* The triangle form is achieved by a CSS hack */
.login-triangle {width: 0;margin-right: auto;margin-left: auto;border: 12px solid transparent;border-bottom-color: #e82c2c;}
.login-header {background: #e82c2c;padding: 20px;font-size: 1.4em;font-weight: normal;text-align: center;text-transform: uppercase;color: #fff;}
.login-container {background: #ebebeb;padding: 12px;}
/* Every row inside .login-container is defined with p tags */
.login p {padding: 12px;}
.login input {box-sizing: border-box;display: block;width: 100%;border-width: 1px;border-style: solid;padding: 16px;outline: 0;font-family: inherit;font-size: 0.95em;}
.login input[type="email"],{background: #fff;border-color: #bbb;color: #555;}
/* Text fields' focus effect */
.login input[type="email"]:focus, .login input[type="password"]:focus {  border-color: #888;}
.login input[type="submit"] {background: #353535;border-color: transparent;color: #fff;cursor: pointer;}
.login input[type="submit"]:hover {background: #17c;}
/* Buttons' focus effect */
.login input[type="submit"]:focus {border-color: #05a;}
@media all and (max-width:450px){
.login {width: 95%;}
}

</style>
 <div class="container">
<br><br>
 <center><img src="${pageContext.request.contextPath}/CinApex.png" style= "width:45%"></center><br><br>
	<div class="row">
		<div class="login">
  <div class="login-triangle"></div>
  
  <h2 class="login-header">Log in</h2>

  <form class="login-container" action="/Cinapex1/Account">
     	  <p><font color = "#e82c2c">User Type: </font>
             <select id="select" name="personType" style="width: 100px; height: 30px">
               <option>Customer</option>
               <option>Employee</option>
             </select>
             </p>
             
    <p><input type="text" placeholder="SSN or Customer Id" id="inputEmail" name="user" required autofocus></p>
    <p><input type="submit" value="Log in"></p>
  </form>
</div>>
	</div>
</div>
<!--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/particles.js/2.0.0/particles.min.js"></script>-->


 </body>
</html>