<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<div class="container">

	<form action="" method="post">
	     <div class="form-group">
				<label for="id">아이디:</label> <input type="text"
					class="form-control" id="id" value="${sMember.id }"
					name="id" readonly="readonly">
			</div>
			
		<div class="form-group">
			<label for="pass">비밀번호:</label> <input type="password"
				class="form-control" id="pass" value="${sMember.pass }" name="pass">
		</div>
		<div class="form-group">
			<label for="pass_check">비밀번호 확인:</label> 
			<input type="password" class="form-control" id="pass_check" placeholder="Enter pass_check"
				name="pass_check">
		</div>

		<div class="form-group">
			<label for="name">이름:</label> <input type="text" id="name"
				name="name" class="form-control" value="${sMember.name }">
		</div>

		<div class="form-group">
			<label for="addr">주소:</label> <input type="text" id="addr"
				name="addr" class="form-control" value="${sMember.addr }">
		</div>
		<button type="button" class="btn btn-secondary" id="btnUpdate">회원수정</button>
	</form>
	<script>
	$("#btnUpdate").click(function(){
		   //유효성검사
		if($("#pass").val()==""){
			alert("비밀번호를 입력하세요");
			$("#pass").focus();
			return false;
		}
		if($("#pass").val()!=$("#pass_check").val()){
			alert("비번이 맞지 않습니다.");
			$("#pass_check").focus();
			return false;
		}
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}
		if($("#addr").val()==""){
			alert("주소를 입력하세요");
			$("#addr").focus();
			return false;
		}
		  data = {
				  "id":$("#id").val(),
				  "name" : $("#name").val(),
				  "pass" : $("#pass").val(),
				  "addr" : $("#addr").val()
		  } 
			$.ajax({  //post  join
				type:"put",
				url :"/app007/member/update",
				contentType: "application/json;charset=utf-8",
				data: JSON.stringify(data)
			})
			.done(function(resp){
					if(resp=="success"){
						alert("회원변경 성공")
						location.href="login";
					}
			})
			. fail(function(e){
					alert("회원변경실패")
			}) //ajax
				
	}) //btnJoin
	
	
	</script>
</div>
<%@ include file="../includes/footer.jsp" %>