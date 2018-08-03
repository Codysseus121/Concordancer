$(document).ready(function() {

    
    $(".kwic").click(function(event) {
     
    	
    	var word = event.target.innerHTML;
    	
    	
    	
    	
    	
    	$.ajax({
            url: '/Concordancer/concordancer',


            data: {

                action : "kwic",
                keyword: word
            },
            type: 'post',
            success: function(response){
                alert("success");
                },
            error: function(e){
                console.log(e);}
		});

	});

 });
