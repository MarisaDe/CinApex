<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>All Employee</title>
 </head>
 <body>
<jsp:include page="menu.jsp"></jsp:include>


    <h3 align = "center">Employee List</h3>
 <p style="color: red;">${errorString}</p>


    <table class="table table-striped" id = "empTable">
       <tr>
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
       </tr>
       <c:forEach items="${EmpList}" var="emp" >
          <tr>
           <form id = "empForm">
          	 <td>${emp.ssn}</td>
          	 <td>${emp.id}</td>
          	 <td>${emp.zipcode}</td>
          	 <td>${emp.lastName}</td>
          	 <td>${emp.firstName}</td>
          	 <td>${emp.address}</td>
          	 <td>${emp.telephone}</td>
             <td>${emp.startDate}</td>
	                  
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