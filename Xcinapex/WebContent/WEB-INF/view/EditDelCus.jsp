<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Add Employee</title>
 </head>
 <body>
<jsp:include page="menu.jsp"></jsp:include>

<script>
function del(x) {
    document.getElementById("custTable").deleteRow(x.rowIndex);
}

</script>

    <h3 align = "center">Customer List</h3>
 <p style="color: red;">${errorString}</p>

 <form action="/Cinapex1/DeleteCust">
    <table class="table table-striped" id = "custTable">
       <tr>
       <th></th>
          <th>Customer Id</th>
          <th>Last Name</th>
          <th>First Name</th>
          <th>Address</th>
          <%--<th>City</th>--%>
          <%--<th>State</th>--%>
          <th>Zipcode</th>
          <th>Telephone</th>
          <th>Email</th>
          <th>Credit Card</th>
          <th>Rating</th>
       </tr>
       <c:forEach items="${CustList}" var="cust" >
          <tr>
          	 <td> <button type = "submit" class="btn-danger">X</button></td>
          	 <td> <input type="hidden" name="custId" value="${cust.custId}"/>${cust.custId}</td>
          	 <td id="fName">${cust.firstName}</td>
          	 <td>${cust.lastName}</td>
          	 <td>${cust.address}</td>
          	 <%-- <td>${cust.city}</td>--%>
          	 <%-- <td>${cust.state}</td>--%>
          	 <td>${cust.zipcode}</td>
          	 <td>${cust.telephone}</td>
          	 <td>${cust.email}</td>
             <td>${cust.cCard}</td>
             <td>${cust.rating}</td>
             
          </tr>
       </c:forEach>
    </table>
    </form>
 </body>
</html>