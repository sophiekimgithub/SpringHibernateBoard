<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring Hibernate Board</title>
	<link rel="stylesheet" href="./resources/css/style.css"/> 
</head>
<body>
 
<table class="withoutBorder">
<tr>
	<th width="60" class="alignLeft"><a href="<c:url value='/list?pageNum=${pageNum}' />">List</a>
	<th>&nbsp;</th>
	<th width="60" class="alignRight"><a href="<c:url value='/delete-${board.no}-board' />">Delete</a>
</tr>
</table> 

<form:form method="POST" modelAttribute="board">

	<c:set var="now" value="<%=new java.util.Date()%>"></c:set>
	<fmt:formatDate var="nowVar" value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>

	<form:input type="hidden" path="no" id="no"/> 
	<form:input type="hidden" path="modify_date" id="modify_date" value="${nowVar}"/> 
	
	<table class="withBorder">
	<tr>
		<td width="60" class="alignCentre"><label for="title">Title</label></td>
		<td class="alignLeft">
			<form:input path="title" type="text" maxlength="255" size="90"/>
			<form:errors path="title" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<form:textarea path="contents" cols="100" rows="30"/>
			<form:errors path="contents" cssClass="error"/>
		</td>
	</tr>
	</table>
	
	<table class="withoutBorder">
	<tr>
		<td class="alignLeft">
			<c:choose>
				<c:when test="${edit}">
					<input type="submit" value="Update"/>
				</c:when>
				<c:otherwise>
					<input type="submit" value="Register"/>
				</c:otherwise>
			</c:choose>
		</td>                 
	</tr>
	</table>
</form:form>
    
</body>
</html>