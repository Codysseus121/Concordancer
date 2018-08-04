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
	alert (start);
	alert(end);
	
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

$(document).ready(function() {

    
	let both = document.getElementById("both");
	both.addEventListener("click", function() {
     
    	
    	var word = $('input[name="keywordbox"]').val();
    	var collocate = $('input[name="collocate"]').val();
    	if (word.length==0)
    		{
    		alert("Please enter a word");
    		}
    	else
    		{
    	
    	
    	$.ajax({
            url: '/Concordancer/concordancer',


            data: {

                action : "collocate",
                keyword: word,
                keyword2: collocate
            },
            type: 'get',
            success: function(response){
            	location.reload();
            	/*$("#tablecon").load(window.location + " #tablecon");*/
                },
            error: function(e){
                console.log(e);}
		});
    		}

	});
	
 });



