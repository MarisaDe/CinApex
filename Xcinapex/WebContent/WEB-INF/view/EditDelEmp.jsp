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
 <jsp:include page="_menu.jsp"></jsp:include>

<script>
$('#myModal').on('show', function() {
    var tit = $('.confirm-delete').data('title');

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
	
	
</script>

    <h3 align = "center">Employee List</h3>
 <p style="color: red;">${errorString}</p>
    <table  class="table table-striped">
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
          <th>Start Date</th>
          <th>Hourly Rate</th>
       </tr>
       <c:forEach items="${EmpList}" var="emp" >
          <tr>
          	 <td><button type = "button" class="btn-danger" href="#" role = "button">X</a></td>
          	 <td>${emp.ssn}</td>
          	 <td>${emp.firstName}</td>
          	 <td>${emp.lastName}</td>
          	 <td>${emp.address}</td>
          	  	 <%-- <td>${emp.city}</td>--%>
          	  <%-- <td>${emp.state}</td>--%>
          	 <td>${emp.zipcode}</td>
          	 <td>${emp.telephone}</td>
             <td>${emp.startDate}</td>
             <td>${emp.hourlyRate}</td>
             
          </tr>
       </c:forEach>
    </table>

 </body>
</html>