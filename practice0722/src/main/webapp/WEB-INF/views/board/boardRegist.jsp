<%@page import="bitedu.bipa.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 작성</title>
<link href="../resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<% MemberVO user = (MemberVO) session.getAttribute("user");%>
<form action="" method="post" enctype="multipart/form-data">
	<table class="tbl">
		<tr>
			<td colspan="3">제목 : <input type="text" name="title" required="required"/></td>
		</tr>
		<tr>
			<c:choose>
				<c:when test="${user != null}">
					<td colspan="3">작성자 : ${user.id}</td>
					<input type="hidden" name="writer" value="${user.id}" />
				</c:when>
				<c:otherwise>
					<td colspan="3">
						작성자 : <input type="text" name="writer" required="required"/>
						비밀번호 : <input type="password" name="password" required="required"/>					
					</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td colspan="3" class="content"><textarea name="content" required="required"></textarea></td>
		</tr>

		<tr>
			<td>첨부파일</td>
			<td colspan="2">
				<img src="http://placehold.it/300X200" width="300" height="200" id="preview">
            	<input type="file" name="upload_file" id="up_image">
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<input type="submit" id="go_board_regist" value="등록" />
				<input type="submit" id="go_board_list" value="목록으로" />
			</td>
		</tr>
	</table>
	</form>
</body>

<script>

	  $('#up_image').on('change',function(e){
	  e.preventDefault();
	  alert("이미지 등록 완료")
	  if (this.files && this.files[0]) {
	    var reader = new FileReader();
	    reader.onload = function(e) {
	      document.getElementById('preview').src = e.target.result;
	    };
	    reader.readAsDataURL(this.files[0]);
	  } else {
	    document.getElementById('preview').src = "";
	  	}
	  });
	  
	$("#go_board_regist").click(function() {
		$("form").attr("action", "./upload.do")
		$("form").submit();
	})
	
	$("#go_board_list").click(function(){
		$("form").attr("method", "get")
		$("form").attr("action", "./list.do")
		$("form").submit();
	})
</script>
</html>