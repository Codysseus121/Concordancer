var oneword;
var functioncalled;


/* Event listener to redirect to projects page */

$(document).ready(function() {

	let doc = document.getElementById("otherprojects");
	doc.addEventListener("click", function() {

		$.ajax({
			url : '/Concordancer/concordancer',

			data : {

				action : "allprojects",

			},
			type : 'post',
			success : function(response) {
				var url = "/Concordancer/jsp/projects.jsp";
				$(location).attr('href', url);

			},
			error : function(e) {
				console.log(e);
			}
		});

	});
});

/* Function to provide long context */

$(document).ready(function() {

	$('#eventdel').on('click', 'tr', function(){
		let fname = $(this).find('td:eq(4)').text();
		let start = $(this).find('td:eq(5)').text();
		let end = $(this).find('td:eq(6)').text();
		
		

		event.preventDefault();

		$.ajax({
			url : '/Concordancer/concordancer',
			
			data : {

				action : "context",
				findex : start,
				lindex : end,
				filename : fname
			},
			dataType:    "text",
		    cache:       false,
			type : 'post',
			success : function(response) {
				$('#contextmodal').modal('show');
				$('#contextmodalbody').html(response);
				
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

/*
 * Function to get collocates
 * 
 */


$(document).ready(function() {
	oneword = "";
	let both = document.getElementById("both");
	both.addEventListener("click", function() {

		var word = $('input[name="keywordbox"]').val();
		var collocate = $('input[name="collocate"]').val();
		if (word.length == 0 || collocate.length == 0) {
			alert("Please enter a word");
		} else {

			$.ajax({
				url : '/Concordancer/concordancer',
				

				data : {

					action : "collocate",
					keyword : word,
					keyword2 : collocate
				},
				type : 'get',
				success : function(data) {
					if (data === "False") {
						
						
						
						var trow= $("#tablecon").find('tr');
	            		$(trow).remove();
	            		$('#mytable').html("No results").css("text-align", "center");
	            	

					}

					else {
						
						$('#head').load("/Concordancer/jsp/Concordances.jsp" +  ' #head');
						oneword=collocate;
						
						
					}

				},
				error : function(e) {
					console.log(e);
				}
			});
		}
		
		
		
	});
	});



$( document ).ajaxComplete(function() {


	 $ctx = $(".table #mytable tr td");
	    $ctx.unmark({
	      done: function() {
	        $ctx.mark(oneword,  {
	            "element": "span",
	            "className": "highlight" });
	      }
	    });
	    
   });
   
   
 



/* function to get keyword on click from index with event listeners. */

$(document).ready(
		function() {
			$(".kwic").click(
					function(event) {
						oneword="";
						event.preventDefault();
						var word = event.target.innerHTML;

						$.ajax({
							url : '/Concordancer/concordancer',

							data : {

								action : "kwic",
								keyword : word
							},
							type : 'get',
							success : function(data) {

								$('#head').load(
										"/Concordancer/jsp/Concordances.jsp"
												+ ' #head');
								
								

							},
							error : function(e) {
								console.log(e);
							}
						});
						 
					});
		});

/*
 * function to get keyword on click from button and input form with event
 * listeners.
 */

$(document).ready(
		function() {

			$("#buttonkwic").click(
					function(event) {
											
						
						oneword="";
						var word = $('input[name="keywordbox"]').val();
						if (word.length === 0) {
							alert("Please enter a word");
						} else {

							$.ajax({
								url : '/Concordancer/concordancer',

								data : {

									action : "kwic",
									keyword : word
								},
								type : 'get',
								success : function(data) {

									if (data === "False") {

										var trow = $("#tablecon").find('tr');
										$(trow).remove();
										$('#mytable').html("No results").css(
												"text-align", "center");

									}

									else {
										
										$('#head').load(
												"/Concordancer/jsp/Concordances.jsp"
														+ ' #head');
										
										
										
									}

								},
								error : function(e) {
									console.log(e);
								}
							});
						}
						 
					});
			
		});

$('#next').on('click', function (event){

	
	var table = document.getElementById("mytable");
	var cell =  table.getElementsByTagName("td");
	var indexvalue = cell[0].innerHTML;
	var direction = "next";
	
	$.ajax({
		url : '/Concordancer/concordancer',

		data : {

			action : "paginate",
			index : indexvalue,
			dir: direction
		},
										
		type : 'get',
		success : function(data) {
				$('#head').load("/Concordancer/jsp/Concordances.jsp" + ' #head');
				

		},
		error : function(e) {
			console.log(e);
		}
	});



	
	alert(index);
	console.log(content);

});
