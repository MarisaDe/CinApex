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
 <jsp:include page="_menuEmp.jsp"></jsp:include>

<script>
$('#myModal').on('show', function() {
    var tit = $('.confirm-delete').data('title');

<<<<<<< HEAD
    $('#myModal .modal-body p').html("Desea eliminar al usuario " + '<b>' + tit +'</b>' + ' ?');
    var id = $(this).data('id'),
    removeBtn = $(this).find('.danger');
})

$('.confirm-delete').on('click', function(e) {
    e.preventDefault();

    var id = $(this).data('id');
    $('#myModal').data('id', id).modal('show');
});

$('#btnYes').click(function() {
    // handle deletion here
    var id = $('#myModal').data('id');
    $('[data-id='+id+']').parents('tr').remove();
    $('#myModal').modal('hide');
    
});

$('table').on('click','.delete',function(){
	$(this).parents('tr').remove();
	});
	
	
=======

function del(x) {
    document.getElementById("empTable").deleteRow(x.rowIndex);
}

>>>>>>> ab6a115047cf43ce14b0c0a39acce33e8ec538f4
</script>

    <h3 align = "center">Employee List</h3>
 <p style="color: red;">${errorString}</p>
<<<<<<< HEAD
    <table  class="table table-striped">
=======
 <form action="/Cinapex1/DeleteEmp">
    <table class="table table-striped" id = "empTable">
>>>>>>> ab6a115047cf43ce14b0c0a39acce33e8ec538f4
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
<<<<<<< HEAD
          <tr><form></form>
          	 <td><button type = "button" class="btn-danger" href="#" role = "button">X</a></td>
          	 <td>${emp.ssn}</td>
          	 <td>${emp.firstName}</td>
=======
          <tr>
          	 <td> <button type = "submit" class="btn-danger">X</button></td>
          	 <td> <input type="hidden" name="ssnOfEmp" value="${emp.ssn}"/>${emp.ssn}</td>
          	 <td id="fName">${emp.firstName}</td>
>>>>>>> ab6a115047cf43ce14b0c0a39acce33e8ec538f4
          	 <td>${emp.lastName}</td>
          	 <td>${emp.address}</td>
          	 <%-- <td>${emp.city}</td>--%>
          	 <%-- <td>${emp.state}</td>--%>
          	 <td>${emp.zipcode}</td>
          	 <td>${emp.telephone}</td>
          	 <td><input type="hidden" name="idOfEmp" value="${emp.id}"/>${emp.ssn}</td>
             <td>${emp.startDate}</td>
<<<<<<< HEAD
             <td>${emp.hourlyRate}</td>
             
=======
             <td>$${emp.hourlyRate}</td>
        
>>>>>>> ab6a115047cf43ce14b0c0a39acce33e8ec538f4
          </tr>
       </c:forEach>
    </table>
    </form>
 </body>
</html>