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

$(document).ready(function() {
	
	$('#mytable').find('tr').click( function(){
		let  fname = $(this).find('td:eq(4)').text();
		  let start = $(this).find('td:eq(5)').text();
		  let end = $(this).find('td:eq(6)').text();
		  alert (start);
		  alert (end);
		  alert(fname);
		  
		
	 
	  event.preventDefault();
	  
	  
	
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
			//location.reload();
		},
		error : function(e) {
			console.log(e);
		}
	});
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

