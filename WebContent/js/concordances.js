  /* function to get keyword on click from index with event listeners. */
    $(".kwic").click(function(event) {
     
    	
    	event.preventDefault();
    	var word = event.target.innerHTML;
    	
    	
    	
    	$.ajax({
            url: '/Concordancer/concordancer',


            data: {

                action : "kwic",
                keyword: word
            },
            type: 'get',
            success: function(data){
            	location.reload();
            	
                },
            error: function(e){
                console.log(e);}
		});

	});

 


/* function to get keyword on click from button and input form with event listeners. */
    
    $("#buttonkwic").click(function(event) {
     
    	
    	var word = $('input[name="keywordbox"]').val();
    	
    	
    	$.ajax({
            url: '/Concordancer/concordancer',


            data: {

                action : "kwic",
                keyword: word
            },
            type: 'get',
            success: function(response){
            	location.reload();
            	/*$("#tablecon").load(window.location + " #tablecon");*/
                },
            error: function(e){
                console.log(e);}
		});

	});


