<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="container">
	<h3>${board.user.username }의글보기</h3>
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
			id="writer" name="writer" value="${board.user.username }"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="content" name="content"
			readonly="readonly">${board.content }</textarea>
	</div>
	<c:if test="${principal.user.username == board.user.username }">
		<div class="form-group text-right">
			<button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정</button>
			<button type="button" class="btn btn-danger btn-sm" id="btnDelete">삭제</button>
		</div>
	</c:if>
	<div align="center">
		<textarea rows="3" cols="50" id="msg"></textarea>
		<button type="button" class="btn btn-secondary btn-sm" id="btnComment">댓글쓰기</button>
	</div>
	<div id="replyResult"></div>
</div>
<script>
var init = function(){
	$.ajax({
		type:"get",
		url : "/reply/list/"+$("#num").val()
	}) //ajax
	.done(function(resp){
		   var str="<table class='table table-hover' >"
			   $.each(resp, function(key, val){
				   str+="<tr>"
				   str+="<td>"+val.user.id+"</td>"
				   str+="<td>"+val.content+"</td>"
				   str+="<td>"+val.regdate+"</td>"
				   str +="<td>삭제</td>"
				   str +="</tr>"
			   }) //each
			   str +="</table>"
			   $("#replyResult").html(str);
	}) //done
}  //init

//댓글추가
$("#btnComment").click(function(){
	if(${empty principal.user}){
		alert("로그인 하세요");
		location.href="/login";
		return;
	}
	 if($("#msg").val()==""){
			alert("댓글을 적으세요")
			return;
	 }
	 var data ={
				"bnum" : $("#num").val(),
				"content" :$("#msg").val()
		}
	 $.ajax({
			type:"POST",
			url : "/reply/insert/"+$("#num").val(),
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data)
		})
		.done(function(resp,status){
			alert(status)
			alert("댓글 추가 성공")
			init();
		})
		.fail(function(){
			alert("댓글 추가 실패");
		});
	
});


//수정폼
$("#btnUpdate").click(function(){
	if(confirm("정말 수정할까요?")){
		location.href="/board/update/${board.num}"
	}
})

//삭제
$("#btnDelete").click(function(){
	if(!confirm("정말 삭제할까요?")) return
	$.ajax({
		type:"delete",
		url : "/board/delete/${board.num}",
		success:function(resp){
			if(resp=="success"){
				alert("삭제성공")
				location.href="/board/list"
			}
		},
		error:function(e){
			alert("삭제실패 : " + e)
		}
	})//ajax
})//btnDelete

init()

</script>