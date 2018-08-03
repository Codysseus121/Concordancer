$(document).ready(function() {

    
    $(".kwic").click(function(event) {
     
    	
    	var kwic = event.target.innerHTML;
    	
    	
    	
    	
    	$.ajax({
            url: '/Concordancer/concordancer',


            data: {

                action : "kwic",
                "kwic": varaiable
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
