/*JQuery code to toggle login & register view
 *
 */
$('.message a').click(function() {
	$('form').animate({
		height : "toggle",
		opacity : "toggle"
	}, "slow");
	$("#messages").html("");
});


/*
 * JQuery to submit login form to server using Ajax for asynchronous request.
 * https://stackoverflow.com/questions/21162524/how-to-make-a-login-form-using-ajax-and-servlet
 */

$('#login').click(function() {
	var user = document.getElementById('loginusername').value;
	var pwd = document.getElementById('loginpassword').value;

	$.ajax({
		type : "POST",
		url : "LoginServlet",
		data : {
			"username" : user,
			"password" : pwd
		},
		success : function(results) {
			if (results == "True") {
				
				$("#messages").html("Success. Redirecting...");
				var url = "jsp/projects.jsp";
				$(location).attr('href', url);
			} 
			else if (results=="False")
			{
				
				$("#messages").html("Invalid login. Please try again.");
				
			}
		},
		error : function()

		{
			alert("failure")

		}
	});
});

/*
 * JQuery to submit registration form to server using Ajax for asynchronous request.
 * https://stackoverflow.com/questions/21162524/how-to-make-a-login-form-using-ajax-and-servlet
 */


$('#register').click(function() {
	var user = document.getElementById('registerusername').value;
	var pwd = document.getElementById('registerpassword').value;

	$.ajax({
		type : "POST",
		url : "RegisterServlet",
		data : {
			"username" : user,
			"password" : pwd,
			
		},
		success : function(data) {
			if (data == 'False') {
				$("#messages").html("Username exists.");;
			} else {
				var url = "jsp/projects.jsp";
				$(location).attr('href', url);
			}
		}
	});
});
