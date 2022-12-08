<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="container">
	<h3>JOIN</h3>
	<div class="form-group">
		<label for="username">이름:</label> <input type="text"
			class="form-control" id="username" placeholder="Enter username"
			name="username">
	</div>
	<div class="form-group">
		<label for="password">비밀번호:</label> <input type="password"
			class="form-control" id="password" placeholder="Enter password"
			name="password">
	</div>
	<div class="form-group">
		<label for="email">이메일:</label> <input type="text"
			class="form-control" id="email" placeholder="Enter email"
			name="email">
	</div>
	<button class="btn btn-secondary" id="btnJoin">회원가입</button>

	<script>
		$("#btnJoin").click(function() {
			if ($("#username").val() == "") {
				alert("아이디 입력하세요")
				$("#username").focus();
				return false;
			}
			if ($("#password").val() == "") {
				alert("비밀번호 입력하세요")
				$("#password").focus();
				return false;
			}
			if ($("#email").val() == "") {
				alert("이메일 입력하세요")
				$("#email").focus();
				return false;
			}
			var dataParam = {
				"username" : $("#username").val(),
				"password" : $("#password").val(),
				"email" : $("#email").val()
			}
			$.ajax({
				type : "POST",
				url : "/register",
				contentType : "application/json;charset=utf-8",
				data : JSON.stringify(dataParam)
			}).done(function(resp) {
				if (resp == "success") {
					alert("회원가입성공")
					location.href="/login"
				} else if (resp == "fail") {
					alert("아이디 중복")
					$("#username").val("")
				}
			}) //done
			.fail(function() {
				alert("회원가입실패")
			})
		})
	</script>
</div>