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


    <h3 align = "center">Employee List</h3>
 <p style="color: red;">${errorString}</p>


    <table class="table table-striped" id = "empTable">
       <tr>

          <th></th>
          <th>SSN</th>
          <th>Employee ID</th>
          <th>Last Name</th>
          <th>First Name</th>
          <th>Address</th>
          <%--<th>City</th>--%>
          <%--<th>State</th>--%>
          <th>Zipcode</th>
          <th>Telephone</th>
          <th>Start Date</th>
          <th>Hourly Rate</th>
       </tr>
       <c:forEach items="${EmpList}" var="emp" >
          <tr>
           <form id = "empForm" action = "/Cinapex1/DeleteEmp">
          	 <td><button type = "submit" class="btn-danger">X</button></td>
          	 <td><input type="hidden" name="ssnOfEmp2" value="${emp.ssn}"/>${emp.ssn}</td>
          	 <td><input type="hidden" name="idOfEmp2" value="${emp.id}"/>${emp.id}</td>
          	 <td><input type="hidden" name="empLastName2" value="${emp.lastName}"/>${emp.lastName}</td>
          	 <td><input type="hidden"name="empFirstName2" value="${emp.firstName}"/>${emp.firstName}</td>
          	 <td><input type="hidden" name="empAddress2" value="${emp.address}"/>${emp.address}</td>
          	 <%-- <td>${emp.city}</td>--%>
          	 <%-- <td>${emp.state}</td>--%>
          	 <td><input type="hidden" name="empZipcode2" value="${emp.zipcode}"/>${emp.zipcode}</td>
          	 <td><input type="hidden" name="empTelephone2" value="${emp.telephone}"/>${emp.telephone}</td>

             <td><input type="hidden" name="empStartDate2" value="${emp.startDate}"/>${emp.startDate}</td>
             <td><input type="hidden" name="empHourly2" value="${emp.hourlyRate}"/>${emp.hourlyRate}</td>
	                        
          </form>
          </tr>
       </c:forEach>
    </table>
    
 <script>

function save() {
		form=document.getElementById("empForm");
        form.action="/Cinapex1/EditEmp";
}
function del() {
		form=document.getElementById("empForm");
        form.action="/Cinapex1/DeleteEmp";
}
function refresh() {
    location.reload();
}
</script>
 </body>
</html>