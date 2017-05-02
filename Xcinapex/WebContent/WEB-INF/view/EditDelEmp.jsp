<%@page import="servlet.EditDelEmp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Edit/Delete Employee</title>
 </head>
 <body>
 <jsp:include page="_menu.jsp"></jsp:include>

<script>

function edit_row(no)
{
 document.getElementById("edit_button"+no).style.display="none";
 document.getElementById("save_button"+no).style.display="block";
	
 var name=document.getElementById("name_row"+no);
 var country=document.getElementById("country_row"+no);
 var age=document.getElementById("age_row"+no);
	
 var name_data=name.innerHTML;
 var country_data=country.innerHTML;
 var age_data=age.innerHTML;
	
 name.innerHTML="<input type='text' id='name_text"+no+"' value='"+name_data+"'>";
 country.innerHTML="<input type='text' id='country_text"+no+"' value='"+country_data+"'>";
 age.innerHTML="<input type='text' id='age_text"+no+"' value='"+age_data+"'>";
}

function save_row(no)
{
 var name_val=document.getElementById("name_text"+no).value;
 var country_val=document.getElementById("country_text"+no).value;
 var age_val=document.getElementById("age_text"+no).value;

 document.getElementById("name_row"+no).innerHTML=name_val;
 document.getElementById("country_row"+no).innerHTML=country_val;
 document.getElementById("age_row"+no).innerHTML=age_val;

 document.getElementById("edit_button"+no).style.display="block";
 document.getElementById("save_button"+no).style.display="none";
}




function del(x) {
    document.getElementById("empTable").deleteRow(x.rowIndex);
}

</script>

    <h3 align = "center">Employee List</h3>
 <p style="color: red;">${errorString}</p>
    <table class="table table-striped" id = "empTable">
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
          	 <td><form action = "/Cinapex1/EditDelEmp"> <button type = "submit" class="btn-danger" role = "button">X</a></button></form></td>
          	 <td value = "ssnOfEmp">${emp.ssn}</td>
          	 <td id = "fName">${emp.firstName}</td>
          	 <td>${emp.lastName}</td>
          	 <td>${emp.address}</td>
          	  	 <%-- <td>${emp.city}</td>--%>
          	  <%-- <td>${emp.state}</td>--%>
          	 <td>${emp.zipcode}</td>
          	 <td>${emp.telephone}</td>
             <td>${emp.startDate}</td>
             <td>$${emp.hourlyRate}</td>
             
          </tr>
       </c:forEach>
    </table>

 </body>
</html>