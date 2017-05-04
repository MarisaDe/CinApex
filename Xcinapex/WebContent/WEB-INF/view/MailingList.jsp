<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
 <title>Cinapex</title>
 </head>
 <body>
 <jsp:include page="menu.jsp"></jsp:include>
 
     <h3 align = "center">Customer Mailing List</h3>
 <div class="container">
 <div class="form-group row">
 

 <p style="color: red;">${errorString}</p>
    <table class = "table table-striped">
       <tr>
          <th>Email</th>
          <th>Address</th>
          <th>ZipCode</th>
       </tr>
       <c:forEach items="${MailingList}" var="mailing" >
          <tr>
             <td>${mailing.email}</td>
             <td>${mailing.address}</td>
             <td>${mailing.zipcode}</td>
            </tr>
       </c:forEach>
    </table>
 </div></div>
    
 </body>
</html>