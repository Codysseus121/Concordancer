



/* function to get keyword on click from index with event listeners. */

$(document).ready(
		function() {
			$(".kwic").click(
					function(event) {

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
