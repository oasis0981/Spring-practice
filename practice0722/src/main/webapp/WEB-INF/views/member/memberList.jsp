<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
<link href="../resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<h1>전체 회원 목록</h1>
	<table class="tbl">
		<tr>
			<td colspan="4">도서목록</td>
		</tr>
		<tr>
			<td>회원번호</td>
			<td>아이디</td>
			<td>전화번호</td>
		</tr>
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.seq}</td>
				<td><a href="detail.do?seq=${member.seq}">${member.id}</a></td>
				<td>${member.phoneNumber}</td>
				<td><a href="delete.do?seq=${member.seq}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>