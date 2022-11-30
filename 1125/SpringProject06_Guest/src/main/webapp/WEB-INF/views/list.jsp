<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guest List</title>
</head>
<body>
	<h3>Guest List(${count })</h3>
	<c:forEach items="${guests }" var="guest">
	이름 : ${guest.name }<br />
	내용 : ${guest.content }<br />
	등급 : ${guest.grade }<br />
		<br />
	</c:forEach>
</body>
</html>