<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>

<head>


   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" type="text/css" href="css/login.css">
   <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
   <link href="https://fonts.googleapis.com/css?family=Crimson+Text|Lora|Merriweather|Playfair+Display" rel="stylesheet">
  



<title>kiWic</title>
</head>


<body>
	<!-- Adapted from: https://codepen.io/colorlib/pen/rxddKy, Copyright (c) 2018 by Aigars Silkalns -->
	<h1>kiWi concordancer</h1>
	


	<div class="login-page">
		<div class="form">
		<div><p id="messages"></p></div>

			<form class="register-form" method="POST" onsubmit="return false;">
				<input type="text" placeholder="username" id="registerusername" name="username" />
				<input type="password" placeholder="password" id="registerpassword" name="password" />

				<button class="btn btn-success" id="register">create</button>
				<p class="message">
					Already registered? <a href="#">Sign In</a>
				</p>
			</form>

			<form class="login-form" method="POST" onsubmit="return false;">
				<input type="text" placeholder="username" id="loginusername" name="username" />
				<input type="password" placeholder="password" id= "loginpassword" name="password" />

				<button class="btn btn-success" id="login">login</button>
				<p class="message">
					Not registered? <a href="#">Create an account</a>
				</p>
			</form>
		</div>
	</div>


   	<script src="js/index.js"></script>
</body>
</html>
