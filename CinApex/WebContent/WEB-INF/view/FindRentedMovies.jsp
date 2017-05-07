<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cinapex</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>

	<h3 align="center">Find Rented Movies</h3>

	<form class="navbar-form navbar-left" action="/Cinapex1/RentedMovies">
		<div class='input-prepend'>
			<font>Category:</font> 
			<select style="width: 160px; height: 45px" name=selector>
				<option>Movie Name</option>
				<option>Type</option>
				<option>Customer Name</option>
				</select>
					<div class="form-group">
						<input type="text" name=search class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
	</form>


</body>
</html>