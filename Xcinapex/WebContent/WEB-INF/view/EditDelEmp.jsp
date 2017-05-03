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
<jsp:include page="menu.jsp"></jsp:include>>

<script>
function del(x) {
    document.getElementById("empTable").deleteRow(x.rowIndex);
}

</script>

    <h3 align = "center">Employee List</h3>
 <p style="color: red;">${errorString}</p>


    <table class="table table-striped" id = "empTable">
       <tr>
       <th></th>
          <th>SSN</th>
          <th>Last Name</th>
          <th>First Name</th>
          <th>Address</th>
          <%--<th>City</th>--%>
          <%--<th>State</th>--%>
          <th>Zipcode</th>
          <th>Telephone</th>
          <th>Employee ID</th>
          <th>Start Date</th>
          <th>Hourly Rate</th>
       </tr>
       <c:forEach items="${EmpList}" var="emp" >
          <tr>
           <form action="/Cinapex1/DeleteEmp">
          	 <td> <button type = "submit" class="btn-danger">X</button></td>
          	 <td> <input type="hidden" name="ssnOfEmp" value="${emp.ssn}"/>${emp.ssn}</td>
          	 <td id="fName">${emp.firstName}</td>
          	 <td>${emp.lastName}</td>
          	 <td>${emp.address}</td>
          	 <%-- <td>${emp.city}</td>--%>
          	 <%-- <td>${emp.state}</td>--%>
          	 <td>${emp.zipcode}</td>
          	 <td>${emp.telephone}</td>
          	 <td><input type="hidden" name="idOfEmp" value="${emp.id}"/>${emp.ssn}</td>
             <td>${emp.startDate}</td>
             <td>${emp.hourlyRate}</td>
          </form>
          </tr>
       </c:forEach>
    </table>
 </body>
</html>