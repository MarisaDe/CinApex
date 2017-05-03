<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rented Movies</title>
</head>
<body><jsp:include page="menu.jsp"></jsp:include>

	<h3 align="center">Rented Movies</h3>
	<table border="1" cellpadding="5" cellspacing="1" align="center" class="table table-striped">
		<tr>
			<th>AccountId</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>CustRepId</th>
			<th>OrderId</th>
			<th>MovieId</th>
			<th>Name</th>
			<th>Type</th>
			<th>Rating</th>
			<th>DistrFee</th>
			<th>NumCopies</th>
		</tr>
		
		<c:forEach items="${RentedList}" var="rented">
			<tr>
				<td>${rented.accountId}</td>
				<td>${rented.firstName}</td>
				<td>${rented.lastName}</td>
				<td>${rented.custRepId}</td>
				<td>${rented.orderId}</td>
				<td>${rented.movieId}</td>
				<td>${rented.name}</td>
				<td>${rented.type}</td>
				<td>${rented.rating}</td>
				<td>${rented.distrFee}</td>
				<td>${rented.numCopies}</td>

			</tr>
		</c:forEach>
	</table>


</body>
</html>