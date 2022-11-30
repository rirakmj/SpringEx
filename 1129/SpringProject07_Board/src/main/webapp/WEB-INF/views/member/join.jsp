<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container">
	<form>
		<div class="row">
			<div class="col">
				<label for="id">아이디:</label> <input type="text" class="form-control"
					id="id" placeholder="Enter UserId" name="id">
			</div>
			<div class="col  align-self-end">
			<!-- 	<button type="button" id="idCheckBtn" class="btn btn-primary">중복확인</button> -->
				<span id="idcheck"></span>
			</div>
		</div>

		<div class="form-group">
			<label for="pass">비밀번호:</label> <input type="password"
				class="form-control" id="pass" placeholder="Enter pass" name="pass">
		</div>
		<div class="form-group">
			<label for="pass_check">비밀번호 확인:</label> <input type="password"
				class="form-control" id="pass_check" placeholder="Enter pass_check"
				name="pass_check">
		</div>

		<div class="form-group">
			<label for="name">이름:</label> <input type="text" id="name"
				name="name" class="form-control">
		</div>

		<div class="form-group">
			<label for="addr">주소:</label> <input type="text" id="addr"
				name="addr" class="form-control">
		</div>
		<button type="button" class="btn btn-secondary" id="btnJoin">회원가입</button>
	</form>
	<script>
    $("#btnJoin").click(function(){
    	//유효성검사
		if($("#id").val()==""){
			alert("아이디를 입력하세요");
			$("#id").focus();
			return false;
		}
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
    	$.ajax({
	    	  type:'post',
	    	  url : '/app007/member/join',
	    	  contentType:'application/json;charset=utf-8',
	    	  data :JSON.stringify(data)
	    }) //ajax
	      	.done(function(resp){
	      		if(resp=="success"){
	       			alert("회원가입을 축하합니다.")
	      			location.href="login"
	      		}else if(resp=="fail"){
	      			//alert("아이디 중복확인하세요")
	      			$("#idcheck").html("<b>아이디 중복 확인하세요</b>")
	      			$("#id").val("");
	      		}
	      	}).fail(function(){
	    		alert("회원가입실패")
	    	})
       })  //btnJoin
</script>


</div>


<%@ include file="../includes/footer.jsp"%>