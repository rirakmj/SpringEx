<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script>
$(function(){
	loadData("","");
	$("#send").click(function(){
		var postString = {
				"name" : $("#name").val(),
				"content" : $("#content").val(),
				"grade" : $("input:radio[name=grade]:checked").val()
		}
		$.ajax({
			type: "post",
			url : "insert" ,
			contentType: "application/json;charset=utf-8",
			data:JSON.stringify(postString) ,
			success: function(resp){
				alert(resp)
				loadData("","");
			},
			beforeSend: showRequest,
			error:function(e){
				alert("실패 : " + e)
			} 
		}) // ajax
	}) //send
	
	$("#btnSearch").click(function(){
		loadData($("#field").val(), $("#word").val());
	})
	
}) //document
function loadData(field, word){
	$.getJSON(
			"list",
			{"field" :field, "word" :word },
			function(resp){
				var str ="<br/>";
				$("#cntDiv").text("개수 : " +resp.count )
				$.each(resp.arr, function(key,val){
					str+=val.num +"   "
					str+="<a href='javascript:fview("+val.num+")'>"+val.name +"</a>  "
					str+=val.content +"   "
					str+=val.grade +"  "
					str +="<a href='javascript:fdelete("+val.num+")'>삭제</a><br/>"
				})
				$("#result").html(str)
			} //resp		
	) //getJSON
}  //loadData
//삭제
function fdelete(num){
	$.ajax({
		type:"DELETE",
		url:"delete/"+num,
	    success:function(resp){
		    if(resp=="success"){
		    	alert("삭제성공")
				loadData("","");
		    }
		},
		error : function(e){
			alert("삭제실패 : " + e)
		}
	}) //ajax
} //fdelete



//상세보기
function fview(num){
	$.getJSON("view", {"num" : num} ,function(val){
		var str = "";
		str += "이름 : " + val.name + "<br/>"
		str += "내용 : " + val.content + "<br/>"
		str += "평가 : " + val.grade + "<br/>"
		str += "작성일 : " + val.created + "<br/>"
		$("#viewDiv").html(str)
	})
}
function showRequest(){
	if($("#name").val()==""){
		alert("글쓴이를 입력하세요")
		$("#name").focus();
		return false;
	}
	if(!$("#content").val()){
		alert("내용을 입력하세요")
		$("#content").focus();
		return false;
	}
	if($("input:radio[name=grade]:checked").length==0){
		alert("평가하세요")
		return false;
	}
	return true;
}
</script>
</head>
<body>
<form action="insert" method="post">
		<table align="center">
			<tr>
				<td >글쓴이</td>
				<td><input type="text" id="name" name="name"></td>
			</tr>
			<tr>
				<td >내 용</td>
				<td><input type="text" size="80" id="content" name="content">
				</td>
			</tr>
			<tr>
				<td >평가</td>
				<td>
				<input  type="radio" name="grade" value="excellent" checked>아주잘함(excellent)
				<input  type="radio" name="grade" value="good"> 잘함(good) 
				<input 	type="radio" name="grade" value="normal"> 보통(normal) 
				<input 	type="radio" name="grade" value="fail"> 노력(fail)</td>
			</tr>
			<tr>
				<td colspan="2">
				 <input type="button" value="입력" id="send">
				</td>
			</tr>
		</table>
		<div align="right">
			<select name="field" id="field">
				<option value="name">이름</option>
				<option value="content">내용</option>
			</select> 
			<input type="text" name="word" id="word"> 
			<input type="button" value="찾기" id="btnSearch">
		</div>
		
		
	</form>
	<hr/>
	<div id ="cntDiv"></div>
	<div id="result"></div>
	<hr/>
	<div id = "viewDiv"></div>
</body>
</html>




