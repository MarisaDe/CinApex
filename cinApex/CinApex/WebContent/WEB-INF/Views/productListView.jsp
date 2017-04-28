<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Movie List</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Movie List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Type</th>
          <th>Rating</th>
          <th>DistrFee</th>
          <th>NumCopies</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${productList}" var="product" >
          <tr>
             <td>${product.Id}</td>
             <td>${product.Name}</td>
             <td>${product.Type}</td>
             <td>${product.Rating}</td>
             <td>${product.DistrFee}</td>
             <td>${product.Delete}</td>
             <td>
                <a href="editProduct?code=${product.Id}">Edit</a>
             </td>
             <td>
                <a href="deleteProduct?code=${product.Id}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="createProduct" >Create Product</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>