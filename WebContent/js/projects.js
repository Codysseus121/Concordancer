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

/* Validate File Upload with help from 
 * https://stackoverflow.com/questions/47941158/jquery-validation-filesize-show-a-human-readable-value.
 * https://stackoverflow.com/questions/33096591/validate-file-extension-and-size-before-submitting-form */
$(function(){
	  $.validator.addMethod('filesize', function (value, element, param) {
	      return this.optional(element) || (element.files[0].size <= param)
	  }, function(size){
	    return "MAX SIZE " + filesize(size,{exponent:2,round:1});
	  });


$("#newProject" ).validate({
    rules: {
        file:{
            required:true,
            accept:"application/pdf,text/plain,text/html",
            filesize: 10000000  //max size 10MB
        },
		projectname: {
			required:true,
			 maxlength: 20
					}
    },messages: {
        file:{
            filesize:" File size must be less than 10 MB.",
            accept:"Please upload .txt, .html or .pdf files only.",
            required:"Please upload file."
        },
    	projectname:{required: "Please enter a name for your project"}
    },
    submitHandler: function(form) {
        form.submit();
    }
}) });