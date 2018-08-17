<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>

<head>


   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" type="text/css" href="css/login.css">
   <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
   <link rel="stylesheet" href="Bootstrap-4/css/bootstrap.min.css">



<title>A2Z</title>
</head>


<body>
	<!-- Adapted partially from: https://codepen.io/colorlib/pen/rxddKy, Copyright (c) 2018 by Aigars Silkalns -->
	<h1>
		A<sup>2</sup>Z
	</h1>
	<h1>Concordancer</h1>


	<div class="login-page">
		<div class="form">
		<div><p id="messages"></p></div>

			<form class="register-form" method="POST">
				<input type="text" placeholder="username" id="registerusername" name="username" />
				<input type="password" placeholder="password" id="registerpassword" name="password" />

				<span class="btn btn-success" id="register">create</span>
				<p class="message">
					Already registered? <a href="#">Sign In</a>
				</p>
			</form>

			<form class="login-form" method="POST">
				<input type="text" placeholder="username" id="loginusername" name="username" />
				<input type="password" placeholder="password" id= "loginpassword" name="password" />

				<span class="btn btn-success" id="login">login</span>
				<p class="message">
					Not registered? <a href="#">Create an account</a>
				</p>
			</form>
		</div>
	</div>


   	<script src="js/index.js"></script>
</body>
</html>
