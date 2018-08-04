$(document).ready(function() {

	let doc = document.getElementById("otherprojects");
	doc.addEventListener("click", function() {
		

		$.ajax({
			url : '/Concordancer/concordancer',

			data : {

				action : "useproject",

			},
			type : 'get',
			success : function(response) {
				var url = "projects.jsp";
				$(location).attr('href', url);
			},
			error : function(e) {
				console.log(e);
			}
		});

	});
});

$(".morecontext").click(function(event) {

	
	event.preventDefault();
	var start = $('.index1').html();/*event.target.innerHTML;*/
	var end = $('.index2').html();
	var fname = $('.filename').html();
	
	$.ajax({
		url : '/Concordancer/concordancer',

		data : {

			action : "context",
			findex : start,
			lindex: end,
			filename: fname
		},
		type : 'post',
		success : function(response) {
			$('#contextmodal').modal('show');
			$('.modal-body').html(response);
		},
		error : function(e) {
			console.log(e);
		}
	});

});

/** Logout function */
$(document).ready(function() {

let doc = document.getElementById("buttonlogout");
doc.addEventListener("click", function() {
	

	$.ajax({
		url : '/Concordancer/concordancer',

		data : {

			action : "logout",

		},
		type : 'get',
		success : function(response) {
			var url = "/Concordancer/concordancer";
			$(location).attr('href', url);
		},
		error : function(e) {
			console.log(e);
		}
	});

});
});
