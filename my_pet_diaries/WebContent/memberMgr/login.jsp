<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인 페이지</title>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
	<link rel="stylesheet" href="/my_pet_diaries/css/join.css">
</head>
<body>
	<%@ include file="../main/top.jsp" %>
	<form action = "../login_proc.do" method="post">	
	<fieldset style = "height: 200px; width: 500px;">
		<legend>로그인</legend>  		
		<input class = "inputForm" type="text" name="id" value="choonham" required="required" placeholder = "ID" />
		<input class = "inputForm" type="password" name="pwd" value="1234" required="required" placeholder = "Password" />
		<div style = "text-align: right;">
			<input class = "inputBtn" type="submit" value="login"/>
			<input class = "inputBtn"  type="reset" value="reset"/>
		</div>
	</fieldset>
</form>
	
</body>
</html>