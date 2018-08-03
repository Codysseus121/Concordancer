$(document).ready(function() {

    
    $(".kwic").click(function(event) {
     
    	event.preventDefault();
    	var word = event.target.innerHTML;  	
    	
    	$.ajax({
            url: '/Concordancer/concordancer',


            data: {

                action : "kwic",
                keyword: word
            },
            type: 'post',
            success: function(data){
            	$("#tablecon").load(window.location + " #tablecon");
                },
            error: function(e){
                console.log(e);}
		});

	});

 });

$(document).ready(function() {

    
    $("#buttoncol").click(function(event) {
     
    	
    	var word = $('input[name="keywordbox"]').val();
    	
    	$.ajax({
            url: '/Concordancer/concordancer',


            data: {

                action : "kwic",
                keyword: word
            },
            type: 'post',
            success: function(data){
            	$("#tablecon").load(window.location + " #tablecon");
                },
            error: function(e){
                console.log(e);}
		});

	});

 });


