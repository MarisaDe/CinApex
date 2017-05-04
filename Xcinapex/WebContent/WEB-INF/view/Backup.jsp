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

<script type="text/javascript">
function getfolder(e) {
    var files = e.target.files;
    var path = files[0].webkitRelativePath;
    var Folder = path.split("/");
    alert(Folder[0]);
}
</script>


	<center>

		<form action="/Cinapex1/DatabaseBacked">
		
			<h3>BackUp DataBase</h3>
			<div class="form-group">
				<label>Path:</label> <input type="text" class="form-control" name="path" id="usr" required autofocus>
			</div>
			<!-- 
			<input type="file" id="flup" onchange="getfolder(event)" webkitdirectory mozdirectory msdirectory odirectory directory multiple />
				 -->
				 <button type="button" class="btn btn-success">Backup</button>
				 
		</form>

	</center>


</body>
</html>