<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
<style>
#book_list {
	border: 1px solid black;
	text-align: center;
	border-collapse: collapse;
}

#book_list tr, #book_list td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h1>전체 도서 목록</h1>
	<table id="book_list">
		<tr>
			<td colspan="3">도서목록</td>
		</tr>
		<tr>
			<td>도서번호</td>
			<td>제목</td>
			<td>저자</td>
		</tr>

		<c:forEach var="book" items="${list}">
			<tr>
				<td>${book.bookSeq}</td>
				<td>${book.title}</td>
				<td>${book.author}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>