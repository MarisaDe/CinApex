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
 <jsp:include page="_menuEmp.jsp"></jsp:include>



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
 
  <h2>Edit a Customer</h2>
  <p>Change as many fields as you like. Only the CUSTOMER ID IS REQUIRED.</p>
  <form action="/Cinapex1/CustAdded">
    <div class="form-group row">
     <div class="col-xs-4">
        <label for="ex3">First Name</label>
        <input class="form-control" id="ex3" type="text" name="CusFname" placeholder="First Name">
      </div>
            <div class="col-xs-4">
        <label for="ex3">Last Name</label>
        <input class="form-control" id="ex3" type="text" name="CusLName" placeholder="Last Name">
      </div>
      <div class="col-xs-4">
        <label for="ex3">Customer Id </label>
        <input class="form-control" id="ex3" type="text" name="CusId" placeholder="xxx-xx-xxxx">
      </div>
      <br><br><br>
      <div class="col-xs-4">
        <label for="ex3">Address</label>
        <input class="form-control" id="ex3" type="text" name="CusAddress" placeholder="Ex: 123 Success Street">
      </div>
       <div class="col-xs-4">
        <label for="ex3">City</label>
        <input class="form-control" id="ex3" type="text" name="CusCity" placeholder="Ex: StonyBrook">
      </div>
      <div class="col-xs-4">
        <label for="ex3">ZipCode</label>
        <input class="form-control" id="ex3" type="number" name="CusZip" placeholder="Ex: 11790"  maxlength="5">
      </div>
      
      <div class="col-xs-4">
        <label for="ex3">State</label>
        <input class="form-control" id="ex3" type="text" name="CusState" placeholder="Ex: NY" maxlength="2">
      </div>
      <br><br><br>
      <div class="col-xs-4">
        <label for="ex3">Telephone</label>
        <input class="form-control" id="ex3" type="text" name="CusPhone" placeholder="Ex: 516-632-8959" maxlength="12">
      </div>
      
    
     <div class="col-xs-4">
        <label for="ex3">Email</label>
        <input class="form-control" id="ex3" type="text" name="Email" placeholder="Ex: customer@gmail.com">
      </div>
      
      <div class="col-xs-4">
        <label for="ex3">Credit Card</label>
        <input class="form-control" id="ex3" type="number" name="cCard" placeholder="Ex: 3423-2342-32342">
      </div>
      
     <div class="col-xs-4">
        <label for="ex3">Rating</label>
        <input class="form-control" id="ex3" type="number" name="rating" placeholder="Ex: 4">
      </div>
      
      
      </div>
      <br><br>
          <center><button type="submit" value="Submit" class = "btn btn-success btn-block">Update Customer</button></center>
  </form>
</div>

<br><br>

    <table class="table table-striped" id = "custTable">
       <tr>
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
          	 <td> <input type="hidden" name="custId" value="${cust.custId}"/>${cust.custId}
          	 <input type="hidden" name="personSSN" value="${cust.SSN}"/> </td>
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
 </body>
</html>