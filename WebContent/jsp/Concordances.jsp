<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	
<%@ page import="java.util.*"%>
<%@ page import="dp.model.concordancer.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control", "no-store");
%>
<%
	response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Bootstrap-4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/concordances.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
<script src="<%=request.getContextPath()%>/js/additional-methods.js"></script>
<script
	src="<%=request.getContextPath()%>/Bootstrap-4/js/bootstrap.min.js"></script>




<title>Concordances</title>
</head>

<body>



	<nav class="navbar sticky-top navbar-expand-sm" id="mybar">
		<div class="container-fluid">
			<div class="navbar-header">

				<ul class="nav navbar-nav">

					<li class="nav-item"><span class="navbar-brand"
						id="projectname">Project:
							${sessionScope.currentproject.projectname}</span></li>
					<li class="nav-item" tabindex="1"><a class="nav-link" href="#"
						id="otherprojects">Other Projects</a></li>
				</ul>

			</div>



			<form class="form-inline" onsubmit="return false;">


				<ul class="nav navbar-nav navbar-right">

					<li class="nav-item"><input type="text" tabindex="2"
						placeholder=" kwic only" name="keywordbox"
						class="form-control mr-sm-2 formkwic"></li>
					<li class="nav-item"><button
							class="btn btn-warning my-2 my-sm-0" id="buttonkwic" tabindex="4"
							type="submit">Get</button></li>
				</ul>
			</form>
			<form class="form-inline" onsubmit="return false;">
				<ul class="nav navbar-nav navbar-right">
					<li class="nav-item" id="col2"><input type="text"
						id="collocateform" class="form-control mr-sm-2"
						placeholder=" collocate" tabindex="3" name="collocate"></li>
					<li class="nav-item" id="both"><button
							class="btn btn-warning my-2 my-sm-0" id="buttoncol" tabindex="5"
							type="submit">Get both</button></li>
				</ul>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li class="nav-item" id="buttonlogout"><button
						class="btn btn-light" tabindex="6" type="button">Logout</button></li>


			</ul>

		</div>
	</nav>







	<!-- The Index -->
	<aside id="content">
		<div id="frame" class="hidden-md-up">
			<ul class="list-group list-group-flush" id="indexlist">


				<c:forEach var="entry" items="${sessionScope.index}">

					<li
						class="list-group-item d-flex justify-content-between align-items-center inentry"
						id="indexline"><a class="kwic" href="#"> <c:out
								value="${entry.key}" /></a> <span class=""><c:out
								value="${entry.value}" /></span></li>

				</c:forEach>

			</ul>
		</div>
	</aside>

	<!-- The Concordances -->

	<div id="eventdel">
		<div id="head">
			<div class="container" id="table">
				<div class="row">
					<div id="table1" class="w-100 p-3 ">

						<table class="table table-hover table-responsive-sm" id="tablecon">

							<thead class="thead-light">
								<tr>
									<th scope="col">No.</th>
									<th scope="col">Left context</th>
									<th scope="col">KWIC</th>
									<th scope="col">Right context</th>
									<th scope="col">File</th>
								</tr>
							</thead>

							<tbody id="mytable">

								<c:forEach var="entry" items="${sessionScope.concordances.concordances}"
									varStatus="myIndex">
									<tr class="trows">

										<td class="tentry" class="itemindex"><c:out
												value="${myIndex.index}" /></td>
										<td class="tentry lcontext contextus"><c:out
												value="${entry.lcontext}" /></td>
										<td class="tentry"><a class="morecontext key" href="#"><c:out
													value="${entry.keyword}" /></a></td>
										<td class="tentry rcontext contextus"><c:out
												value="${entry.rcontext}" /></td>
										<td class="tentry filename"><c:out
												value="${entry.filename}" /></td>
										<td class="tentry index1" style="display: none"><c:out
												value="${entry.index1}" /></td>
										<td class="tentry index2" style="display: none"><c:out
												value="${entry.index2}" /></td>

									</tr>
								</c:forEach>


							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Loader -->


	<div class="gif-loader" id="loader" style="display: none">
		<img src="../images/source.gif" class="img-fluid img-thumbnail" />
	</div>

	<!-- Modal -->
	<div class="modal fade" id="contextmodal" tabindex="-1" role="dialog"
		aria-labelledby="contextmodal" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header" style="background-color: #76b852;">
					<h5 class="modal-title" id="contextModalLongTitle">Larger
						context</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="contextmodalbody">...</div>
				<div class="modal-footer" style="background-color: #76b852;">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>

				</div>
			</div>
		</div>
	</div>


	<script src="<%=request.getContextPath()%>/js/concordances.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.mark.min.js"></script>
		



</body>
</html>