<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container">
	<div class="form-group">
		<label for="id">아이디:</label> <input type="text" id="id" name="id"
			class="form-control" placeholder="Enter ID" />
	</div>

	<div class="form-group">
		<label for="pass">비밀번호:</label> <input type="password"
			class="form-control" id="pass" placeholder="Enter pass" name="pass">
	</div>
	<button type="button" class="btn btn-primary" id="btnLogin">로그인</button>
	<script>
	$("#btnLogin").click(function(){
		$.ajax({
			type:'post',
			url : '/app007/member/login',
			contentType :" application/json;charset=utf-8",
			data : JSON.stringify({"id" : $("#id").val(), "pass":$("#pass").val()})
		})
		.done(function(resp){
			if(resp=="no"){
				 alert("회원이 아닙니다. 회원가입하세요");
				  location.href="/app007/member/join";
			}else if(resp=="success"){
				 alert("로그인 성공")
				  location.href="/app007/"
			}else{
				 alert("비밀번호를 확인하세요")  
			}
		})
		.fail(function(){
			alert("로그인 실패")
		})
		
	})
	
	</script>


</div>


<%@ include file="../includes/footer.jsp"%>