<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<td><input type="text" value="${userInfo.id}" name="id" id="user_id" required/>
					<button id="check_id">중복 확인</button></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" value="${userInfo.phoneNumber}" name="phone_number" id="phone_number"/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" value="${userInfo.password}" name="pwd" id="pwd" /></td>
			</tr>
			<tr>
				<td>권한</td>
				<td>
					<label><input type="radio" name="is_admin" class="is_admin" value="0" checked/>일반</label>
					<label><input type="radio" name="is_admin" class="is_admin" value="1" />관리자</label>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" id="regist_user_info" value="회원가입" /></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		let idChecked = false;
		$("#check_id").click(function(e){
			e.preventDefault();
			console.log($("#user_id").val());
			$.ajax({
				url: "/test/rest/"+$("#user_id").val(),
				type: 'get',
				success:(data) => {
					if (data == 'true') {
						alert("이미 존재하는 아이디입니다.")
						return;
					}
					alert("사용가능한 아이디입니다.")
					idChecked = true;
					$("#user_id").attr("readonly","true");
				},
				error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    }
			})
		})
		
		$("#regist_user_info").click(function(e){
			e.preventDefault();
			if (!idChecked) {
				alert("아이디 중복확인을 해주세요.")
				return;
			}
			
			if($("#phone_number").val() == "") {
				alert("전화번호를 입력해주세요.")
				return;
			}
			
			if($("#pwd").val() == "") {
				alert("비밀번호를 입력해주세요.")
				return;
			}
			
			$("form").attr("action", "regist.do");
			alert("등록 성공");
			$("form").submit();
		})
	</script>
</body>
</html>