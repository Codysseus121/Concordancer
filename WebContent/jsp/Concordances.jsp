<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
    <%@ page import="dp.model.concordancer.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Concordances</title>
</head>

<body>
<h1>Concordances</h1>
<h1>${sessionScope.currentproject.projectname}</h1>


  
  <c:forEach var="entry" items="${sessionScope.index}">
  <c:out value="${entry.key}"/>
  <span>: </span> <c:out value="${entry.value}"/><br/>
</c:forEach>


</body>
</html>