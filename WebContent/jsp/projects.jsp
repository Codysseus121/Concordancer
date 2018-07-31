<%@ page language="java" contentType="text/html; UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
    <%@ page import="dp.model.concordancer.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html lang="en">
<head>
  <title>Projects</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="../Bootstrap-4/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="../css/projects.css">
  <script src="../js/jquery-3.3.1.min.js"></script>
  <script src="../js/jquery.validate.js"></script>
  <script src="../js/additional-methods.js"></script>
  <script src="../Bootstrap-4/js/bootstrap.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  

</head>
<body>

<div id="container">
<h1>${sessionScope.currentSessionUser.username}'s Projects</h1>
<br>
<div class="container py-4">
<div class="card-deck">

<!-- Checks whether this user has any projects. If not, present New Project card -->
<c:choose>



<c:when test="${empty sessionScope.projects}">

<div class="card" style="width: 15rem;">
    <div class="card-body">
    <h5 class="card-title">New Project</h5>
      </div>

  <div class="card-body">
  <button type="button" class="btn btn-primary btn-sm"  data-toggle="modal" data-target="#newprojectmodal">Create Project</button>

     </div>
</div>

</c:when>



<c:otherwise>
<!-- Present project cards -->

<c:forEach items="${sessionScope.projects}" var="Project">

<div id="${Project.project_id}" class="card " style="width: 15rem;">

  <div class="card-body">
    <h5 class="card-title">${Project.projectname}</h5>
  </div>


  <div class="card-body">
  <button type="button" name="${Project.projectname}" id="${Project.project_id}" class="btn btn-primary btn-sm use">Use</button>

   <a href="#" name="${Project.projectname}" id="${Project.project_id}" data-toggle="modal" data-target="#deleteprojectmodal">
    <button type="button" class="btn btn-danger btn-sm">Delete</button></a>
  </div>
</div>


   </c:forEach>



   <div class="card" style="width: 15rem;">
    <div class="card-body">
    <h5 class="card-title">New Project</h5>
      </div>

  <div class="card-body">
  <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#newprojectmodal">
  Create Project</button>

     </div>
</div>

   </c:otherwise>

</c:choose>
</div></div>


<!-- Bootstrap Modals -->

<div class="modal fade" id="newprojectmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
     <h5 class="modal-title">Create new project</h5>


        <button type="button" class="close" name="submit" value="submit" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form  method="post" id="newProject" enctype="multipart/form-data" action="/Concordancer/concordancer">
  <div class="form-group">
   <input type="text" class="form-control" id="projectname" name="projectname" placeholder="Project Name">
    <br>
    <input type="file" name="file" class="form-control-file" id="files" multiple/>

  </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close btn-sm" data-dismiss="modal">Cancel</button>
        <button type="submit" id="uploadbutton" name="action" value="newproject" class="btn btn-success">Submit</button>


      </div>
      </form></div>
    </div>
  </div>
</div>




</div>
<div id="ajaxGetUserServletResponse"></div>
<div class="modal fade" id="deleteprojectmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
 <form method="post" action="" onsubmit="return false;"> <div class="modal-dialog modal-dialog-centered form-group" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

      </div>
      <div class="modal-footer">

        <button type="button" class="btn btn-secondary close" data-dismiss="modal">Cancel</button>
        <input type="hidden" id="parameter_pid" name="parameter_pid" value="">
        <button id="Delete" class="btn btn-danger" data-dismiss="modal">Delete</button>

        </div>
      </div>
    </div>  </form>
  </div>


<script src="../js/projects.js"></script>


</body>
</html>
