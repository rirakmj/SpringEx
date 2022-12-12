<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container">
	<input type="hidden" id="bnum" value="${board.num }"/>
	<table class="table">
		<tr>
			<th>제목 :</th>
			<td colspan="2">${board.title }</td>
			<th>Writen by [${board.writer }]</th>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.regdate }</td>
			<th>조회수</th>
			<td>${board.readcount }</td>
		</tr>
		<tr>
			<td colspan="4">
			<textarea readonly="readonly" cols="150" rows="5">${board.content }</textarea>
			</td>
		</tr>
	</table>
	<div style="text-align:right">
	<sec:authorize access="isAuthenticated()">
	<c:if test="${pInfo.username }==${board.writer }">
		<button type="button" onclick="location.href='update/${board.num}'" class="btn btn-warning">수정</button>
		<button type="button" onclick="javascript:del()" class="btn btn-danger">삭제</button>
	</c:if>
	</sec:authorize>
	</div>
</div>
<br/>
<br/>
<div align="center">
	<textarea rows="3" cols="50" id="msg"></textarea>
	<button type="button" class="btn btn-secondary btn-sm" id="btnComment">댓글쓰기</button>
</div>
<hr/>
<div id="replyCount"></div>
<div id="replyResult"></div>

<script>
var init = function(){
	$.ajax({
		type : "get",
		url : "/app08/reply/commentList/${board.num}",
// 		data : {bnum : $("#bnum").val()}
	})
	.done(function(resp){
		$("#replyCount").html(resp.count + "개의 댓글");
		var str = "";
		$.each(resp.carr,function(key,val){
			str += val.userid + ' ';
			str += val.content + ' ';
			str += val.regdate + ' ';
			<sec:authorize access="isAuthenticated()">
				if("${pInfo.username}" == val.userid){
					str += "<a href='javascript:fdel("+val.cnum+")'>삭제</a>";
				}
			</sec:authorize>
			str += "<br/>"
		})
		$("#replyResult").html(str);
	})
	.fail(function(e){
		alert("FAIL");
	})
}

function fdel(cnum){
	$.ajax({
		type : "delete",
		url : "/app08/reply/commentDelete/" + cnum,
	})
	.done(function(resp){
		alert(resp + "번 글 삭제 완료.");
		init();
	})
	.fail(function(e){
		alert("Error ! " + e);
	});
}

$("#btnComment").click(function(){
	<sec:authorize access="isAnonymous()">
		alert("로그인이 필요한 서비스입니다.");
		return;
	</sec:authorize>
	if($("#msg").val() == ""){
		alert("댓글 입력하셈.");
		return;
	}
	data = {
		"content" : $("#msg").val(),
		"bnum" : $("#bnum").val(),
		<sec:authorize access="isAuthenticated()">
		"userid" : "${pInfo.username}"
	</sec:authorize>
	};
	$.ajax({
		type : "post",
		url : "/app08/reply/commentInsert",
		contentType : "application/JSON;charset=UTF-8",
		data : JSON.stringify(data),
		success : function(resp){
			if(resp == "needLogin"){
				alert("로그인이 필요합니다.");
				return;
			}
			if(resp == "success") alert("성공");
			init();
		} ,
		error : function(e) {
			alert("Error! : " + e);
		}
	})
})
</script>
<script>
	function del(){
		if(confirm("정말 삭제하시겠습니까?")){
			$.ajax({
				type : "DELETE",
				url : "/app08/board/delete/"+${board.num},
				success : function(resp){
					if(resp == "success"){
						alert("삭제성공");
						location.href="/app08/board/list";
					}
				},
				error : function(e) {
					alert("Error : " + e);
				}
			})
		}
	}
init();
</script>
<%@ include file="../include/footer.jsp"%>