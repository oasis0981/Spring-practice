<%@page import="bitedu.bipa.utils.DateCalculation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link href="../resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<h1>방명록</h1>
	<form action="./search.do" method="get">
	   <div id="search">
	      <select name="selectBox">
	         <option value="title">제목</option>
	         <option value="writer">작성자</option>
	         <option value="seq">번호</option>
	      </select> 
	      <input type="text" id="searchBox" name="searchBox">
	      <button type="submit" id="searchBtn">검색</button>
	   </div>
	</form>
	<table class="tbl">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="post" items="${list}">
			<tr>
				<td>${post.seq}</td>
				<td><a href="detail.do?seq=${post.seq}">${post.title}</a></td>
				<td>${post.writer}</td>
				<td>${DateCalculation.getDate(post.uploadTime)}</td>
				<td>${post.view}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><a href="./view_regist.do"><button>방명록 작성하기</button></a></td>
		</tr>
	</table>
</body>
</html>