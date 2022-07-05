<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table,tr,td{
border:1px solid black;
border-collapse:collapse;
}
</style>
</head>
<body>
<%@ include file="header.html" %>
<table>
	<tr>
		<td>Number</td>
		<td>Name</td>
		<td>Author</td>
		<td>Price</td>
	</tr>
	
	<c:forEach items="${books}" var="each">
	<tr>
		<td><c:out value="${each.bookNumber}" /></td>
		<td><c:out value="${each.bookName}" /></td>
		<td><c:out value="${each.bookAuthor}" /></td>
		<td><c:out value="${each.bookPrice}" /></td>
		
		<td><a href="edit?id=<c:out value='${each.bookNumber}' />">Edit</a></td>
		<td><a href="delete?id=<c:out value='${each.bookNumber}' />">delete</a></td>
		</tr>
	
	</c:forEach>
	
</table>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>