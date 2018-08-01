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


<title>Concordances</title>
</head>

<body>
	<div class="container-fluid">
		<div class="navbar-lg">





			<nav class="navbar navbar-light  bg-light" style="background-color: #e3f2fd;">
				
				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link active" href="#">Project:
							${sessionScope.currentproject.projectname}</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Projects</a></li>
			
				</ul>

				<div class="form">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="find"
							aria-label="Recipient's username" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn btn-info" type="button">kWIC</button>
							<button class="btn btn btn-info" type="button">collocates</button>
						</div>
						<button type="button" class="btn btn-light">Logout</button>
					</div>

				</div>

			</nav>
		</div>

		<!-- The Index -->
		<div class="row">
			<div class="table-responsive col-md-2">
				<table class="table table-striped">

					<thead>
						<tr>
							<th scope="col">KWIC</th>
							<th scope="col">Freq</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach var="entry" items="${sessionScope.index}">
							<tr>
								<td><c:out value="${entry.key}" /></td>
								<td><c:out value="${entry.value}" /></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<!-- The Concordances -->

			<div class="table-responsive col-lg-10 col-md-6">
				<table class="table table-striped">

					<thead>
						<tr>
							<th scope="col">LC</th>
							<th scope="col">KWIC</th>
							<th scope="col">RC</th>
							<th scope="col">FILE</th>
						</tr>
					</thead>

					<tbody>


						<tr>
							<td>Conc</td>
							<td>Conc</td>
							<td>Conc</td>
							<td>Conc</td>
						</tr>


					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>