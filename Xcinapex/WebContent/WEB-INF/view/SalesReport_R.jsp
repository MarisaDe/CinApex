<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SalesReport</title>
</head>
<body>
	<jsp:include page="_menuEmp.jsp"></jsp:include>
		
		<h2>Monthly Sales for :</h2>
		<h2>${salesReport.date}</h2>
		<h3>$ ${salesReport.totalSales}</h3>
	
		<table border="1" cellpadding="5" cellspacing="1" align = "center" class="table table-striped">
        <tr>
          <th>Account Id</th>
          <th>Date Opened</th>
          <th>Account Type</th>
          <th>Customer Id</th>
       </tr>
       <c:forEach items="${salesReport.accounts}" var="acc" >
          <tr>
             <td>${acc.id}</td>
             <td>${acc.date}</td>
             <td>${acc.type}</td>
             <td>${acc.customerId}</td>
             
          </tr>
       </c:forEach>
    </table>

</body>
</html>