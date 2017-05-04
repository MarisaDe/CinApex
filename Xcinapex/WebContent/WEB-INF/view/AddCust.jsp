<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Customer</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>


	<div class="container">
		<h2>Add a Customer</h2>
		<p>Fill out all fields below to add a Customer to the database:</p>
		<form action="/Cinapex1/CustAdded">
			<div class="form-group row">
				<div class="col-xs-4">
					<label for="ex3">First Name</label> <input class="form-control"
						id="ex3" type="text" name="CusFname" placeholder="First Name"
						required autofocus>
				</div>
				<div class="col-xs-4">
					<label for="ex3">Last Name</label> <input class="form-control"
						id="ex3" type="text" name="CusLName" placeholder="Last Name"
						required autofocus>
				</div>
				<div class="col-xs-4">
					<label for="ex3">Customer Id </label> <input class="form-control"
						id="ex3" type="text" name="CusId" placeholder="xxx-xx-xxxx"
						required autofocus>
				</div>
				<br>
				<br>
				<br>
				<div class="col-xs-4">
					<label for="ex3">Address</label> <input class="form-control"
						id="ex3" type="text" name="CusAddress"
						placeholder="Ex: 123 Success Street" required autofocus>
				</div>
				<div class="col-xs-4">
					<label for="ex3">City</label> <input class="form-control" id="ex3"
						type="text" name="CusCity" placeholder="Ex: StonyBrook" required
						autofocus>
				</div>
				<div class="col-xs-4">
					<label for="ex3">ZipCode</label> <input class="form-control"
						id="ex3" type="number" name="CusZip" placeholder="Ex: 11790"
						maxlength="5" required autofocus>
				</div>

				<div class="col-xs-4">
					<label for="ex3">State</label> <input class="form-control" id="ex3"
						type="text" name="CusState" placeholder="Ex: NY" maxlength="2"
						required autofocus>
				</div>
				<br>
				<br>
				<br>
				<div class="col-xs-4">
					<label for="ex3">Telephone</label> <input class="form-control"
						id="ex3" type="text" name="CusPhone"
						placeholder="Ex: 516-632-8959" maxlength="12" required autofocus>
				</div>


				<div class="col-xs-4">
					<label for="ex3">Email</label> <input class="form-control" id="ex3"
						type="text" name="Email" placeholder="Ex: customer@gmail.com"
						required autofocus>
				</div>

				<div class="col-xs-4">
					<label for="ex3">Credit Card</label> <input class="form-control"
						id="ex3" type="text" name="cCard"
						placeholder="Ex: 3423-2342-32342" required autofocus>
				</div>

				<div class="col-xs-4">
					<label for="ex3">Rating</label> <input class="form-control"
						id="ex3" type="number" name="rating" placeholder="Ex: 4">
				</div>
				<div class="col-xs-4">
					<label for="ex3">Account Id</label> <input class="form-control"
						id="ex3" type="number" name="acctId" placeholder="Ex: 4">
				</div>
				<div class="col-xs-4">
					<label for="ex3">Account Type</label> <select id="select"
						name="accountType" style="width: 80px; height: 30px">
						<option>Limited</option>
						<option>Unlimited-1</option>
						<option>Unlimited-2</option>
						<option>Unlimited-3</option>
						<select>
				</div>


			</div>
			<br>
			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
		</form>
	</div>


</body>
</html>