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
 
    <h3 align = "center">Your ID</h3>
 <p style="color: red;">${errorString}</p>
    <table border="1" cellpadding="5" cellspacing="1" align = "center">
       <tr>
          <th>ID</th>
       </tr>
       <c:forEach items="${Login}" var="emp" >
          <tr>
             <td>${employee.ssn}</td>
             
          </tr>
       </c:forEach>
    </table>
 
    
 </body>
</html>