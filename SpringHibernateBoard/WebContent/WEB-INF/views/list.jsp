<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"	 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring Hibernate Board</title>
	<link rel="stylesheet" href="./resources/css/style.css"/> 
</head>
<body>
	
	<table class="withoutBorder">
	<tr>
		<th class="alignLeft">
			<a href="<c:url value='/new' />">Write</a>
		</th>
	</tr>
	</table> 
	
	<table class="withoutBorder">
	<tr>
		<td class="alignRight">Total : ${totalCount}</td>
	</tr>
	</table>

	<table class="withBorder">
	<tr>
		<th width="60" class="alignCentre">No</th>
		<th width="250">Title</th>
		<th>Contents</th>
		<th width="130" class="alignCentre">Modify Date</th>
	</tr>
   
    <c:forEach items="${board}" var="board" varStatus="status">
    
    <c:set var="noIdx" value="${status.count + rowNum*(pageNum-1)}" />
   	<c:set var="titleSubStr" value="${fn:substring(board.title, 0, 40)}" />
	<c:set var="contentsSubstr" value="${fn:substring(board.contents, 0, 80)}" />
	<fmt:parseDate var="modifyDate" pattern="yyyy-MM-dd'T'HH:mm:ss.SSS"  value="${board.modify_date}" />
	<fmt:formatDate var="modifyDate" value="${modifyDate}" pattern="dd/MM/yyyy HH:mm"/>
	
    <tr>
        <td class="alignCentre"><a href="<c:url value='/edit-${board.no}-board-${pageNum}' />">${noIdx}</a></td>        
        <td><a href="<c:url value='/edit-${board.no}-board-${pageNum}' />">${titleSubStr}</a></td>
        <td><a href="<c:url value='/edit-${board.no}-board-${pageNum}' />">${contentsSubstr}</a></td>
        <td class="alignCentre"><a href="<c:url value='/edit-${board.no}-board-${pageNum}' />">${modifyDate}</a></td>
    </tr>
    
    </c:forEach>
    </table>
   
	<table class="withoutBorder">
	<tr>
		<td class="alignCentre">${pageStr}</td>
	</tr>
	</table>
   
</body>
</html>