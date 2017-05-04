<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Edit Delete Movie</title>
 </head>
 <body>
 <jsp:include page="menu.jsp"></jsp:include>

<script>
function save() {
		form=document.getElementById("someForm");
        form.action="/Cinapex1/EditCust"
        form.submit();
}
function del() {
		form=document.getElementById("someForm");
        form.action="/Cinapex1/DeleteCust"
        form.submit();
}
</script>

<div class="container">
    <h3 align = "center">Customer List</h3>
 <p style="color: red;">${errorString}</p>

 <form action="/Cinapex1/DeleteCust">
  	<h2> Delete Customer</h2>
  	<font color = red> **WARNING: THIS IS NON-REVERSIBLE**</font><br><br>
 <div class="form-group row">
     <div class="col-xs-4">
        <label for="ex3">Customer ID </label>
        <input class="form-control" id="ex3" type="text" name="custId" placeholder="Enter an Id to DELETE" required autofocus>
      </div>
      <br>
<button type="submit" value="Delete" class="btn btn-danger">Delete</button>
</div>
 </form>
 

<br><br>
<h2> Edit Customer</h2>
<div class="container">
    <div class="form-group row">
    <table class="table table-striped" id = "custTable">
       <tr>
       		<th></th>
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
	       <form id = "someForm" action = "/Cinapex1/EditCust">
	          <tr>
	          	<td>
	          	<td><button type = "submit" class="btn btn-primary" name = "save" onclick="save(); refresh();">Save</button></td>
	          	 <td> <input type="hidden" name="custId" value="${cust.custId}"/>${cust.custId}
	          	 <input type="hidden" name="personSSN" value="${cust.SSN}"/> </td>
	          	 <td><input name="custFirstName" value="${cust.firstName}"/></td>
	          	 <td><input name="custLastName" value="${cust.lastName}"/></td>
	          	 <td><input name="custAddress" value="${cust.address}"/></td>
	          	 <%-- <td><input required name="custCity" value="${cust.city}"/></td>--%>
	          	 <%-- <td><input required name="custState" value="${cust.state}"/></td>--%>
	          	 <td><input required name="custZipcode" value="${cust.zipcode}"/></td>
	          	 <td><input required name="custTelephone" value="${cust.telephone}"/></td>
	          	 <td><input required name="custEmail" value="${cust.email}"/></td>
	             <td><input required name="custCCard" value="${cust.CCard}"/></td>
	             <td><input required name="custRating" value="${cust.rating}"/></td>
	     
          </tr></form>
       </c:forEach>
    </table>
    </div>
    </div>
 </body>
 <script>
function refresh() {
    location.reload();
}
</script>
</html>