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
#sign_up {
	width: 80%;
	border: 1px solid black;
	text-align: center;
	border-collapse: collapse;
}

#sign_up tr, #sign_up td {
	border: 1px solid black;
	padding: 15px;
}
</style>
</head>
<body>
	<h1>회원 정보 수정</h1>
	<form action="" method="post">
		<table id="sign_up">
			<tr>
				<td colspan="2">회원 가입</td>
			</tr>
			<tr>
				<td>ID</td>
				<td>
					<input type="text" value="${userInfo.id}" name="id" id="user_id"/>
					<button id="check_id">중복 확인</button>	
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" value="${userInfo.phoneNumber}" name="phone_number"/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" value="${userInfo.password}" name="pwd"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" id="update_user_info" value="회원가입" disabled/>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$("#update_user_info").click(function(){
			$("form").attr("action", "./update.do");
			$("form").submit();
		})
		
		$("#check_id").click(function(e){
			e.preventDefault();
			$.get("/test/member/checkId="+$("#user_id").val(),
					function(res){
						console.log(res)
					},
				"json"
			)
		})
	</script>
</body>
</html>