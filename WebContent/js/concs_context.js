$(document).ready(function() {

    
	let doc=document.getElementById("otherprojects");
	   doc.addEventListener("click", function()  {
	   alert('clicked');
	   
	   $.ajax({
           url: '/Concordancer/concordancer',


           data: {

               action : "useproject",
               
           },
           type: 'get',
           success: function(response){
        	   var url = "projects.jsp";
				$(location).attr('href', url);   },
           error: function(e){
               console.log(e);}
		});


	   
   });
});
   
   $(".morecontext").click(function(event) {
     
    	alert("context");
    	event.preventDefault();
    	var word = event.target.innerHTML;  	
    	
    	$.ajax({
            url: '/Concordancer/concordancer',


            data: {

                action : "context",
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



