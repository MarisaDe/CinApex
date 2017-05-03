<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
 </head>
 <body>
<jsp:include page="menu.jsp"></jsp:include>


    <h3 align = "center">Edit Employee List</h3>
 <p style="color: red;">${errorString}</p>


    <table class="table table-striped" id = "empTable">
       <tr>
          <th></th>
          <th>SSN</th>
          <th>Employee ID</th>
          <th>Zipcode</th>
          <th>Last Name</th>
          <th>First Name</th>
          <th>Address</th>
          <%--<th>City</th>--%>
          <%--<th>State</th>--%>
          <th>Telephone</th>
          <th>Start Date</th>
          <th>Hourly Rate</th>
       </tr>
       <c:forEach items="${EmpList}" var="emp" >
          <tr>
           <form id = "empForm" action = "/Cinapex1/EditEmp">
          	 <td><button type = "submit" class="btn btn-primary" name = "save" onclick="refresh()">Save</button></td>
          	 <td><input type="hidden" name="ssnOfEmp" value="${emp.ssn}"/>${emp.ssn}</td>
          	 <td><input type="hidden" name="idOfEmp" value="${emp.id}"/>${emp.id}</td>
          	 <td><input type="hidden" name="empZipcode" value="${emp.zipcode}"/>${emp.zipcode}</td>
          	 <td><input name="empLastName" value="${emp.lastName}"/></td>
          	 <td><input name="empFirstName" value="${emp.firstName}"/></td>
          	 <td><input required name="empAddress" value="${emp.address}"/></td>
          	 <%-- <td>${emp.city}</td>--%>
          	 <%-- <td>${emp.state}</td>--%>
          	 <td><input required name="empTelephone" value="${emp.telephone}"/></td>

             <td><input required name="empStartDate" value="${emp.startDate}"/></td></td>
             <td><input required name="empHourly" value="${emp.hourlyRate}"/></td></td>
	                        
          </form>
          </tr>
       </c:forEach>
    </table>
    
 <script>

function refresh() {
    location.reload();
}
</script>
 </body>
</html>