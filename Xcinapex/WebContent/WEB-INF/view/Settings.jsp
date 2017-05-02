<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Settings</title>
 </head>
 <body>
 <jsp:include page="menu.jsp"></jsp:include>
 
    <h3 align = "center">Your Settings</h3>
 <p style="color: red;">${errorString}</p>
    <table border="1" cellpadding="5" cellspacing="1" align = "center">
      
<<<<<<< HEAD
       <h3>Account Type :${loggedInUser.custId}</h3>
=======
       <h3>Account Type :${sessionScope.personType }</h3>
       
       <td><input type="text" value="<%= session.getAttribute("personType") %>" /></td>
>>>>>>> 89f5bbef80a50bbb10ba7a6da936df7d951cb821
    </table>
 Username from session:<c:out value="${sessionScope.loggedInUser.firstName } + ${sessionScope.loggedInUser.lastName}"/>
    
 </body>
</html>