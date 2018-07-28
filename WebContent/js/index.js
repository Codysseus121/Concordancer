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
 * JQuery to validate form & submit to server using the JQuery validate plugin
 * and Ajax method for asynchronous request. Adapted from:
 * https://stackoverflow.com/questions/14171179/how-to-validate-a-login-form-with-jquery
 * https://stackoverflow.com/questions/21162524/how-to-make-a-login-form-using-ajax-and-servlet
 */

/*
 * JQuery to submit form to server using Ajax for asynchronous request.
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

$('#register').click(function() {
	var user = document.getElementById('registerusername').value;
	var pwd = document.getElementById('registerpassword').value;

	$.ajax({
		type : "POST",
		url : "LoginServlet",
		data : {
			"username" : user,
			"password" : pwd
		},
		success : function(data) {
			if (data == 'True') {
				$(location).attr('href', 'jsp/projects.jsp');
			} else {
				alert('Fail....');
			}
		}
	});
});
