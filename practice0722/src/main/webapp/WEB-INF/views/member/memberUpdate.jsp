<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bitedu.bipa.test.utils.DateCalculation"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
	<h1>회원 정보 수정</h1>
	<form action="" method="post">
		<table id="member_list">
			<tr>
				<td colspan="2">회원 정보</td>
			</tr>
			<tr>
				<td>회원번호</td>
				<td><input type="text" value="${userInfo.seq}" disabled id="user_seq" name="user_seq"/></td>
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
				<td colspan="2">
					<button id="update_user_info">회원정보 수정</button>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$("#update_user_info").click(function(){
			$("form").attr("action", "/update.do?seq="+$("#user_seq").val());
			$("form").submit();
		})
	</script>
</body>
</html>