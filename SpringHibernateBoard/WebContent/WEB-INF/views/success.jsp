<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring Hibernate Board</title>
	<link rel="stylesheet" href="./resources/css/style.css"/> 
</head>
<body>
<table class="withoutBorder">
<tr>
	<th class="alignLeft"><a href="<c:url value='/list?pageNum=${pageNum }' />">List</a>
	<th>message : ${success}</th>
	
</tr>
</table> 
     
</body>
 
</html>