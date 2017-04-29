<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--<link rel="stylesheet" href="../css/bootstrap.min.css">  --> 
<!-- <script src="../js/bootstrap.min.js"></script>   -->
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<style>

.navbar-search {
  margin-top: 4px;
}

.navbar .btn, .navbar .btn-group {
  margin-top: 1px;
}

.navbar-search select {
  position: relative;
  left: 84px;
  z-index: 99;
}

.navbar-search input {
  padding-left: 84px;
}

.navbar-search select,
.navbar-search input {
margin-bottom: 0px;
}


</style>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">
      <img alt="Logo" src="${pageContext.request.contextPath}/CinApex.png" style="max-width:150px; margin-top: -5px;">
      </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
 
           <form class="navbar-form navbar-left">
                <div class='input-prepend'>
                        <font color = "white">Category: </font>
                    <select style="width: 80px; height: 30px">
                        <option>Title </option>
                        <option>Genre</option>
                        <option>Actor</option>
                        <select>
	        <div class="form-group">
	          <input type="text" class="form-control" placeholder="Search">
	        </div>
	        <button type="submit" class="btn btn-default">Submit</button>
	      </form>
	       </div>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Account <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</body>
</html>