<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bitedu.bipa.test.utils.DateCalculation"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#member_list {
	width: 80%;
	border: 1px solid black;
	text-align: center;
	border-collapse: collapse;
}

#member_list tr, #member_list td {
	border: 1px solid black;
	padding: 15px;
}
</style>
</head>
<body>
	<h1>회원 상세</h1>
	<form action="" method="post">
		<table id="member_list">
			<tr>
				<td colspan="2">회원 정보</td>
			</tr>
			<tr>
				<td>회원번호</td>
				<td>${userInfo.seq}
				<input type="hidden" value="${userInfo.seq}" id="user_seq"/>
				</td>
			</tr>
			<tr>
				<td>ID</td>
				<td>${userInfo.id}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>${userInfo.password}</td>
			</tr>
			<tr>
				<td>대출가능권수</td>
				<td>${userInfo.maxBook}</td>
			</tr>
			<tr>
				<td>대출정지기간</td>
				<td>${DateCalculation.getDate(userInfo.stopDate)}</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" id="go_view_update" value="수정"/></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$("#go_view_update").click(function(e) {
			alert("go");
			$("form").attr("action", "view_update.do?seq="+$("#user_seq").val())
			$("form").submit();
		})
	</script>
</body>
</html>