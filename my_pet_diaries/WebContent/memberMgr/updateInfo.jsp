<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원정보 페이지</title>
	<script src = "../js/script.js"></script>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
	<link rel="stylesheet" href="/my_pet_diaries/css/join.css">
</head>
<body>
	<%@ include file="../main/top.jsp" %>
	<form action="../update_info.do" method="post" name="regForm">
		<fieldset>
			<legend>회원 정보 수정</legend>
			<input class = "inputForm" type="text" name="id" size="15" required="required" placeholder="ID" readonly = "readonly" value = "${idKey }"/>
			<input class = "inputForm" type="password" name="pwd" size="15" required="required" placeholder="Password"/><br/>
			<input class = "inputForm" type="password" name="repwd" size="15" required="required" placeholder="Confirm Password"/><br/>
			<input class = "inputForm" type="text" name="nickname" size="15" required="required" placeholder="Nickname"  value = "${param.nickname}"/><br/>
			<input class = "inputForm" type="text" name="petname" size="15" required="required" placeholder="Pet Name" value = "${param.petname }"/><br/>
			<input class = "inputForm" type="email" name="email" size="27" placeholder="E-mail"  value = "${param.email }"/><br/>
			<input class = "inputForm" type="tel" name="phone" size="27" placeholder="Phone Number" value = "${param.phone }"/><br/>
			<div style = "text-align: center; margin: 0; margin-top: 30px">
				<input class = "myButton" type="button" value="저장" onclick="inputCheck()"/>
			</div>
		</fieldset>
	</form>
</body>
</html>