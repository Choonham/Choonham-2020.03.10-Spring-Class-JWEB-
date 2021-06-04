<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입 페이지</title>
	<script src = "../js/script.js"></script>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
	<link rel="stylesheet" href="/my_pet_diaries/css/join.css">
</head>
<body>
	<%@ include file="../main/top.jsp" %>
	<form action="../join_proc.do" method="post" name="regForm">
		<fieldset>
			<legend>회원가입</legend>
			<input class = "inputForm" type="text" name="id" size="15" required="required" placeholder="ID"/>
			<input class = "inputForm" type = "hidden" name = "reid" size = "15" />
			<input class = "inputBtn" type="button" value="중복 확인" onclick="idCheck(this.form.id.value)"/><br/>
			<input class = "inputForm" type="password" name="pwd" size="15" required="required" placeholder="Password"/><br/>
			<input class = "inputForm" type="password" name="repwd" size="15" required="required" placeholder="Confirm Password"/><br/>
			<input class = "inputForm" type="text" name="nickname" size="15" required="required" placeholder="Nickname"/><br/>
			<input class = "inputForm" type="text" name="petname" size="15" required="required" placeholder="Pet Name"/><br/>
			<input class = "inputForm" type="email" name="email" size="27" placeholder="E-mail"/><br/>
			<input class = "inputForm" type="tel" name="phone" size="27" placeholder="Phone Number"/><br/>
			<div style = "text-align: center; margin: 0; margin-top: 30px">
				<input class = "myButton" type="submit" value="회원가입" onclick="inputCheck()"/>
				<input class = "myButton" type="reset" value="다시쓰기"/>
			</div>
		</fieldset>
	</form>

</html>