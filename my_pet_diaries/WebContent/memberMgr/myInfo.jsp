<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
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
	<form action="updateInfo.jsp" method="post" name="infoForm">
		<fieldset>
			<legend>내 정보</legend>
			<input class = "inputForm" type="text" name="id" size="15" required="required" placeholder="ID" readonly = "readonly" value = "${idKey }"/>
			<input class = "inputForm" type="password" name="pwd" size="15" required="required" placeholder="Password"/><br/>
			<input type = "hidden" name = "originPwd" value = "${info.getPwd() }"/>
			<input class = "inputForm" type="text" name="nickname" size="15" required="required" placeholder="Nickname" readonly = "readonly" value = "${info.getNickName() }"/><br/>
			<input class = "inputForm" type="text" name="petname" size="15" required="required" placeholder="Pet Name" readonly = "readonly" value = "${info.getPetName() }"/><br/>
			<input class = "inputForm" type="email" name="email" size="27" placeholder="E-mail" readonly = "readonly"  value = "${info.getEmail() }"/><br/>
			<input class = "inputForm" type="tel" name="phone" size="27" placeholder="Phone Number" readonly = "readonly"  value = "${info.getPhone() }"/><br/>
			<div style = "text-align: center; margin: 0; margin-top: 30px">
				<input class = "myButton" type="button" value="회원 수정" onclick="check()"/>
			</div>
		</fieldset>
	</form>
</body>
</html>