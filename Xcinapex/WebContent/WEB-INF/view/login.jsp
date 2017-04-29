<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Login - CinApex</title>

    <!-- Bootstrap core CSS -->
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="WEB-INF/css/login.css" rel="stylesheet"><!-- Not currently working-->
  </head>

  <body>
  <style>
  body{
  background-color: #1c1c1c;
  color: #f20000;
  }
  .btn-primary, .btn-primary:hover, .btn-primary:active, .btn-primary:visited {
    background-color: #f20000;
    border-color: #f20000
}
  </style>
    <div class="container">

	 <center><img alt="Logo" src="${pageContext.request.contextPath}/CinApex.png" style="max-width:500px; margin: 20% 0% 5%;"></center>
      <form class="form-signin">
        <h3 class="form-signin-heading">Customer ID or SSN</h3>
          use: 111-11-1111
        <label for="inputEmail" class="sr-only">Customer ID / SSN</label>
        <input type="email" id="inputEmail" name = "user" class="form-control" placeholder="Email address" required autofocus>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->
  </body>
</html>