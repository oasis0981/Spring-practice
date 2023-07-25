<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 상세</title>
<link href="../resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<table class="tbl">
		<tr>
			<td colspan="3">${post.title}</td>
		</tr>
		<tr>
			<td>작성자 : ${post.writer}</td>
			<td>작성시간 : ${post.uploadTime}</td>
			<td>조회수 : ${post.view}</td>
		</tr>
		<tr>
			<td colspan="3" class="content">${post.content}</td>
		</tr>

		<tr>
			<td>첨부파일</td>
			<c:choose>
				<c:when test="${post.uploadFile != null}">
					<td colspan="2">
						<img src="/images/${post.uploadFile}" width="300" height="200" id="preview"><br /><br />
						<a href="./download.do?fileName=${post.uploadFile}">다운로드</a>
					</td>
				</c:when>
			<c:otherwise>
				<td colspan="2">업로드 파일이 없습니다.</td>
			</c:otherwise>
		</c:choose>
		</tr>
		<tr>
			<td colspan="3">
				<button id="go_board_list">목록으로</button>
				<button id="board_delete">삭제</button>
		</tr>
	</table>
</body>
<script>
	$("#go_board_list").click(function() {
		location.href = "./list.do";
	})
	
	$("#board_delete").click(function(){
		let inputPwd = prompt("비밀번호를 입력하세요.");
	})
</script>
</html>