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
	<c:if test="${sessionScope.sMember.id == board.writer}">
		<div class="form-group text-right">
			<button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정</button>
			<button type="button" class="btn btn-danger btn-sm" id="btnDelete">삭제</button>
			<a href="/app007/delete/${board.num }">삭제</a>
		</div>
	</c:if>
	<br /> <br />
	<div align="center">
		<textarea rows="3" cols="50" id="msg"></textarea>
		<button type="button" class="btn btn-secondary btn-sm" id="btnComment">댓글쓰기</button>
	</div>
	<hr />
	<div id="replyResult"></div>
</div>
<script>
	var init = function() {
		$.ajax({
			type : 'get',
			url : '/app007/reply/commentList/${board.num}'
		/* 	  url : '/app007/reply/commentList',
		 data : {bnum : $("#num").val()} */
		}).done(
				function(resp) {
					var str = "";
					$.each(resp, function(key, val) {
						str += val.userid + '  '
						str += val.content + ' '
						str += val.regdate + ' '
						if("${sessionScope.sMember.id}"==val.userid){
						str += "<a href='javascript:fdel(" + val.cnum
								+ ")'>삭제</a><br/>"
						}
					})//each
					$("#replyResult").html(str)
				})//done
		.fail(function(e) {
			alert("실패")
		}) //fail
	} //init

	//댓글삭제
	function fdel(cnum) {
		$.ajax({
			type : "Delete",
			url : '/app007/reply/delete/' + cnum
		}) //ajax
		.done(function(resp) {
			alert(resp + "번 글 삭제 완료")
			init()
		}).fail(function(e) {
			alert("댓글 삭제 실패 : " + e)
		})
	} //fdel

	//댓글쓰기
	$("#btnComment").click(function() {
		if(${empty sessionScope.sMember}){
			alert("로그인하세요")
			location.href="/app007/member/login"
			return;
		}
		if ($("#msg").val() == "") {
			alert("댓글 입력하세요")
			return;
		}
		data = {
			"bnum" : $("#num").val(),
			"content" : $("#msg").val()
		}
		$.ajax({
			type : "post",
			url : '/app007/reply/commentInsert',
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(data),
			success : function(resp) {
				if (resp == "success")
					alert("댓글추가성공");
				init();
			},
			error : function() {
				alert("댓글 추가 실패")
			}
		}) //ajax
	}) //btnComment

	/////////////////////////
	//수정폼
	$("#btnUpdate").click(function() {
		if (!confirm('정말수정할까요')) // false
			return false;
		location.href = "/app007/update/${board.num}"
	});

	//삭제
	$("#btnDelete").click(function() {
		if (!confirm('정말삭제할까요')) {// false
			return false;
		}
		$.ajax({
			type : "DELETE",
			url : "/app007/delete/${board.num}",
			success : function(resp) {
				if (resp == "success") {
					alert("삭제성공");
					location.href = "/app007/list"
				}
			},
			error : function(e) {
				alert("삭제 실패 : " + e)
			}
		}) //ajax
	}) //btnDelete

	init()
</script>
<%@ include file="includes/footer.jsp"%>





