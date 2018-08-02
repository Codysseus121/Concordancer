<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dp.model.concordancer.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../Bootstrap-4/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/concordances.css">
<script src="../js/jquery-3.3.1.min.js"></script>
  <script src="../js/jquery.validate.js"></script>
  <script src="../js/additional-methods.js"></script>
  <script src="../Bootstrap-4/js/bootstrap.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>


<title>Concordances</title>
</head>

<body>

<div id="dwrapper">
	
		<div id="topbar">
			

<ul id="toplist" >
	<li class="el"><a href="#">Project: ${sessionScope.currentproject.projectname}</a></li>
	<li class="el"><a href="#">Other Projects</a></li>
	<li class="el"><form><input type="text" placeholder="find" maxlength="50" size="30" aria-label="Find Keyword" aria-describedby="basic-addon2">
</form></li>								
	<li class="el"><button class="btn btn btn-info" type="button">kWIC</button></li>
	<li class="el"><button class="btn btn btn-info" type="button">collocates</button></li>
	<li class="el"><button type="button" class="btn btn-light">Logout</button></li>
</ul>
			
</div>


		<!-- The Index -->

<aside id="frame">
<h3>Index</h3>

		<ul class="list-group list-group-flush">
		
			
			<c:forEach var="entry" items="${sessionScope.index}">

				<li class="list-group-item d-flex justify-content-between align-items-center inentry"
					id="indexline"><c:out value="${entry.key}" /> <span
					class="badge badge-primary badge-pill"> <c:out
							value="${entry.value}" /></span></li>
					</c:forEach>
		</ul>

	</aside>

	<!-- The Concordances -->
	<div class="container" id="table">
	<div class="row">
	<div id="table" class="w-100 p-3">
		<div class="table-responsive col-lg-12 col-md-6">
			<table class="table table-hover">

				<thead  class="thead-light">
					<tr>
						<th scope="col">LC</th>
						<th scope="col">KWIC</th>
						<th scope="col">RC</th>
						<th scope="col">FILE</th>
					</tr>
				</thead>

				<tbody>

				<c:forEach var="entry" items="${sessionScope.index}">
					<tr>
						
							<td class="tentry"><c:out value="${entry.key}" /></td>
							<td>:</td>
							<td><c:out value="${entry.value}" /></td>
							<td>Conc</td>
						
					</tr>
					</c:forEach>


				</tbody>
			</table>
		</div></div></div></div>
</div>



</body>
</html>