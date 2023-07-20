<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<style>
table {
	margin: 50px auto;
	width: 70%;
	border-collapse: collapse;
	border-top: 1px solid #aaa;
}

tr, td {
	border-bottom: 1px solid #aaa;
	padding: 10px;
	text-align: center;
	font-size: 0.8rem;
}

#header {
	height: 60px;
	font-size: 1rem;
}

#content {
	display: none;
	border: none;
	height: 100px;
	text-align: center;
}
</style>
</head>
<body>
	<table id="boardTable">
		<tr>
			<th colspan="5" id="header">방명록🐾</th>
		</tr>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성시간</td>
			<td>조회수</td>
		</tr>
	</table>
	<script type="text/javascript">
    $(document).ready(function() {
      getList();

    });

    function getList() {
      $.get("/board/restful/", function(data) {
        console.log(data);
        displayBoardList(data); // 글 목록을 표에 표시하는 함수 호출
      }, "json");
    }

    function displayBoardList(data) {
      let table = $("#boardTable");

      $.each(data, function(index, board) {
        let row = $("<tr>");
        row.append($("<td>").text(board.id));
        row.append($(`<td id="post${board.id}">`).text(board.title));
        row.append($("<td>").text(board.writer));
        row.append($("<td>").text(board.writeTime));
        row.append($("<td>").text(board.views));
        
        
        let content =$(`<tr id="content${board.id}">`);
        content.append($("<td colspan='5'>").text(board.content));
        $(`.content${board.id}`).css("display", "none")
        
        table.append(row);
        table.append(content);
        
        $(`#post${board.id}`).click(function(){
          	if ($(`.content${board.id}`).css("display") == "none") {
          	  $(`.content${board.id}`).css("display", "block")
          	} else {
          	  $(`.content${board.id}`).css("display", "none")
          	}
        })
        
      });
    }
    
    function loadDetail(id) {
      $.get(`/board/restful/${id}`,
          function(data) {
        	displayDetail(data.content);
      }, "json")
    }
    
    function displayDetail(content){
    	let row = $("<tr>");
    	row.append($("<td colspan='5'>").text(content));
    	$("#boardTable").append(row)
    }
    
  </script>
</body>
</html>
