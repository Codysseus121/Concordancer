/* function to show modal on click and perform delete operation */

$('#deleteprojectmodal').on('show.bs.modal', function(e) {

	let project_id = e.relatedTarget.id;
	let project_name = e.relatedTarget.name;
	let project_div = document.getElementById(project_id);
	$(this).find('.modal-body').html('Delete project: ' + project_name + "?");
	$('#parameter_pid').val(project_id);

	$('#Delete').click(function() {
		$(".img-fluid").toggle();

		$.ajax({
			url : '/Concordancer/concordancer',

			data : {

				parameter_pid : project_id,
				"action" : "projectdelete"
			},
			type : 'post',
			success : function(response) {
				$(".img-fluid").toggle();
				project_div.remove();
			},
			error : function(e) {
				console.log(e);
			}
		});

	});

});

/* function for Use button */

$('.use').click(function(event)

{
	$(".img-fluid").toggle();
	let projectno = event.target.id;

	$.ajax({

		url : '/Concordancer/concordancer',
		data : {
			action : "useproject",
			project_id : projectno
		},
		type : 'post',
		success : function() {
			var url = "Concordances.jsp";
			$(location).attr('href', url);
		},
		error : function() {
			console.log("Error");
		}

	});

});

/*
 * Validate File Upload with help from
 *   
 */
$(function() {
	$.validator.addMethod('filesize', function(value, element, params) {
		var lg = element.files.length; // get length
		var items = element.files[0];
		var fileSize = 0;

		if (lg > 0) {
			for (var i = 0; i < lg; i++) {

				fileSize = fileSize + element.files[i].size; // get file size of all files

			}
		}

		return this.optional(element) || (params >= fileSize)
	});

	$("#newProject").validate({
		rules : {
			file : {
				required : true,
				accept : "application/pdf,text/plain,text/html",
				filesize : 10000000
			// max size 10MB
			},
			projectname : {
				required : true,
				maxlength : 20
			}
		},
		messages : {
			file : {
				filesize : " File size must be less than 10 MB.",
				accept : "Please upload .txt, .html or .pdf files only.",
				required : "Please upload file."
			},
			projectname : {
				required : "Please enter a name for your project"
			}
		},
		submitHandler : function() {
			$("#newprojectmodal").hide();
			$(".img-fluid").toggle();

			form.submit();

		}
	})
});

/*
 * Logout function
 */

/** Logout function */
$(document).ready(function() {

	$('#logout').click(function() {

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
