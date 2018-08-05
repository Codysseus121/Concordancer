
/* function to get keyword on click from index with event listeners. */
   
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
            type: 'get',
            success: function(data){
            	
            	location.reload();
            	
                },
            error: function(e){
                console.log(e);}
		});

	});
});

 


/* function to get keyword on click from button and input form with event listeners. */
    
$(document).ready(function() {
	
	$("#buttonkwic").click(function(event) {

     
    	var word = $('input[name="keywordbox"]').val();
    	
    	
    	$.ajax({
            url: '/Concordancer/concordancer',


            data: {

                action : "kwic",
                keyword: word
            },
            type: 'get',
            success: function(data){
            	
            	if (data == "False")
            		{
            		
            		
            		$('#message').modal('show');
            		                          		
            		}
            	
            	else
            		{location.reload();}
            	
                },
            error: function(e){
                console.log(e);}
		});

	});
});

/* http://jsfiddle.net/MrPolywhirl/cbLsc81f/
	 * 
	 
	function onReady(callback) {
	    var intervalID = window.setInterval(checkReady, 1000);

	    function checkReady() {
	        if (document.getElementsByTagName('body')[0] !== undefined) {
	        	$(".loader").toggle();
	        	 document.getElementsByClassName("kwic").onclick = function() { return false; } 
	            window.clearInterval(intervalID);
	            callback.call(this);
	        }
	    }
	}

	function show(id, value) {
	    document.getElementById(id).style.display = value ? 'block' : 'none';
	}

	onReady(function () {
	    $('#loader').hide();
	    document.getElementsByClassName("kwic").onclick = function() { return true; } 
	});*/

