<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>

<div class="container">
	<h3>${board.writer }의글보기</h3>
	<div class="form-group">
		<label for="num">글번호:</label> <input type="text" class="form-control"
			id="num" name="num" value="${board.num }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="title">제목:</label> <input type="text" class="form-control"
			id="title" name="title" value="${board.title }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="pwd">글쓴이:</label> <input type="text" class="form-control"
			id="writer" name="writer" value="${board.writer }"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="content" name="content"
			readonly="readonly">${board.content }</textarea>
	</div>

		<div class="form-group text-right">
			<button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정</button>
			<button type="button" class="btn btn-danger btn-sm" id="btnDelete">삭제</button>
		</div>

	<br /> <br />
	<div align="center">
		<textarea rows="3" cols="50" id="msg"></textarea>
		<button type="button" class="btn btn-secondary btn-sm" id="btnComment">댓글쓰기</button>
	</div>
	<div id="replyResult"></div>
</div>
<script>
var init = function(){
	$.ajax({
	   type:'get',
	   url : '/reply/commentList',
	   data : {"bnum" : $("#num").val()}
	}) //ajax
	.done(function(resp){
		var str ="<table class='table table-hove mt-3'>"
		$.each(resp, function(key, val){
				str+="<tr>"
				str+="<td>"+val.userid+"</td>"
				str+="<td>"+val.content+"</td>"
				str+="<td>"+val.regdate+"</td>"
				str +="<td><a href='javascript:fdel("+val.cnum+")'>삭제<a/></td></tr>"
	     })  //each
		str +="</table>"
		$("#replyResult").html(str);
	}) //done
}
//댓글삭제
function fdel(cnum){
	$.ajax({
		type:"delete",
		url:"/reply/del/"+cnum
	})
	.done(function(resp){
		alert(resp+"번 글 삭제 성공")
		init()
	})
	.fail(function(){
		alert("댓글 삭제 실패")
	})
}
//댓글추가
$("#btnComment").click(function(){
	if($("#msg").val()==""){
		alert("댓글을 입력하세요")
		return false;
	}
	data = {
			"bnum" : $("#num").val(),
			"content" : $("#msg").val()
	}
	$.ajax({
		type:'post',
		url : '/reply/commentInsert',
		 contentType:"application/json;charset=utf-8",
		data : JSON.stringify(data),
	})
	.done(function(res){
		alert("댓글추가 성공")
		init();
	})
	
})

$("#btnDelete").click(function(){
	//if (!confirm("정말 삭제할까요?")) return false;
	if (confirm("정말 삭제할까요?")==false) return false;
	$.ajax({
		type:"delete",
		url : '/delete/${board.num}',
		success:function(resp){
				alert(resp +'번 글 삭제성공')
				location.href='/list'
		}
	}) //ajax
})//btnDelete
$("#btnUpdate").click(function(){
	if (confirm("정말 수정할까요?")) {
	   location.href="/update/${board.num}"
	}
})
init()
</script>







