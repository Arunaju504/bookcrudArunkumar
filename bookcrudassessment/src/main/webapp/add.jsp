<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.html" %>
<div>
<c:out value="${booksToEdit}"></c:out>

</div>
<p style="color:green"><c:out value="${message}"></c:out></p>

<c:if test="${empty booksToEdit}">
<form action="BookServet" method="POST">
	<input type="text" name="bookNumber"/>
	<input type="text" name="bookName"/>
	<input type="text" name="bookAuthor"/>
	<input type="text" name="bookPrice"/>
	<input type="submit" value="ADD"/>
</form>
</c:if>

<c:if test="${! empty booksToEdit}">
<form action="update" method="GET">
	<input type="text" name="bookNumber" value="${booksToEdit.bookNumber }" readonly="readonly"/>
	<input type="text" name="bookName" value="${booksToEdit.bookName }"/>
	<input type="text" name="bookAuthor" value="${booksToEdit.bookAuthor }"/>
	<input type="text" name="bookPrice" value="${booksToEdit.bookPrice }"/>
	<input type="submit" value="Update" />
</form>
</c:if>



<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>