<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/bootstrap.min.css">   
<script src="../js/bootstrap.min.js"></script>   

<div style="background: #E0E0E0; height: 55px; padding: 5px;">
  <div style="float: left">
     <h1>My Site</h1>
  </div>
 
  <div style="float: right; padding: 10px; text-align: right;">
 
     <!-- User store in session with attribute: loginedUser -->
     Hello <b>${loginedUser.userName}</b>
   <br/>
     Search <input name="search" >
 
  </div>
 
</div>