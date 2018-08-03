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




<title>Concordances</title>
</head>

<body>

	<div class="dwrapper">


		<nav id="mybar">

			<ul id="topbar">
				<li class="el"><a href="#" id="projectname">Project:
						${sessionScope.currentproject.projectname}</a></li>
				<li class="el right"><a href="#" id="otherprojects">Other
						Projects</a></li>
			</ul>

			<ul>
				<li class="el right"><button id="buttonlog" type="button">Logout</button></li>
				<li class="el right"><button id="buttonkwic" type="button">Get
						both</button></li>
				<li class="el right"><input type="text"
					placeholder=" collocate" id="collocate"></li>
				<li class="el right"><span><strong>+</strong></span></li>
				<li class="el right"><button id="buttoncol" type="submit" onsubmit="">Get</button></li>
				<li class="el right"><input type="text" placeholder=" kwic only" name="keywordbox" class="formkwic"></li>

			</ul>
		</nav>



		<!-- The Index -->

		<aside id="frame">
			<ul class="list-group list-group-flush" id="indexlist">


				<c:forEach var="entry" items="${sessionScope.index}">

		<li class="list-group-item d-flex justify-content-between align-items-center inentry" id="indexline">
		<a class="kwic"
				href="../concordancer?action=kwic&value=<c:out value='${entry.key}'/>"><c:out value="${entry.key}" /></a>
						<span class=""><c:out value="${entry.value}" /></span></li>

				</c:forEach>		
				
			</ul>

		</aside>

		<!-- The Concordances -->
		<div class="container" id="table">
			<div class="row">
				<div id="table" class="w-100 p-3 ">

					<table class="table table-hover" id="tablecon">

						<thead class="thead-light">
							<tr>
								
								<th scope="col">KWIC</th>
								<th scope="col">FILE</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="entry" items="${sessionScope.concordances}">
								<tr>

									<td class="tentry"><c:out value="${entry}" /></td>
									
								</tr>
							</c:forEach>


						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

<script src="../js/concordances.js"></script>

</body>
</html>