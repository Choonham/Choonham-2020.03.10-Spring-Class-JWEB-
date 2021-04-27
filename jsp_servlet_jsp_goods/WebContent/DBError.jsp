<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오라클 데이터베이스 에러</title>
</head>

<body>
	<h3>데이터베이스 에러 발생</h3>
	발생 에러 메시지 : <%= exception.getMessage() %>
</body>
</html>