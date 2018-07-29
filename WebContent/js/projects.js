/* function to show modal on click and perform delete operation */

$('#deleteprojectmodal').on('show.bs.modal', function (e) {

	
	let project_id = e.relatedTarget.id;
	let project_name = e.relatedTarget.name;
	let project_div=document.getElementById(project_id);
    $(this).find('.modal-body').html('Delete project: ' + project_name +"?" );
    $('#parameter_pid').val(project_id);


    $('#Delete').click (function ()
    		{

    			$.ajax({
                    url: '/Concordancer/concordancer',


                    data: {

                        parameter_pid : project_id,
                        "action": "projectdelete"
                    },
                    type: 'post',
                    success: function(response){
                        project_div.remove();
                        },
                    error: function(e){
                        console.log(e);}
    			});

    		});

    });

/* function for Use button */
$('.use').click(function (event)
		{
	let project_id = event.target.id;


	$.ajax({

		url: '/Concordancer/concordancer',
		data: { action: "useproject", project_id : project_id},
		type: 'post',
		success: function ()
		{window.location.href="Concordances.jsp"},

        error: function(){
            console.log("Error");}

	});

		});
