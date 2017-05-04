<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Backup DataBase</title>
</head>
<body><jsp:include page="menu.jsp"></jsp:include>

	<center>

		<form action="/Cinapex1/DatabaseBacked">
		
			<h3>BackUp DataBase</h3>
			<div class="form-group">
				<label>Path:</label> <input type="text" class="form-control" name="path" id="usr" required autofocus>
			</div>
			<button type="submit" class="btn btn-success">BackUp</button>
				
		</form>

	</center>


</body>
</html>