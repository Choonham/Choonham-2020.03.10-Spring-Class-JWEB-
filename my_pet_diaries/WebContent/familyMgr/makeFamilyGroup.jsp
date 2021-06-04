<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>가족 생성 페이지</title>
	<script src = "../main/script.js"></script>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
	<link rel="stylesheet" href="/my_pet_diaries/css/writeForm.css">
</head>
<body>
	<%@ include file="../main/top.jsp" %>
	
	<form action="../make_family.do" method="post" name="famForm">
	<div style = "text-align: center; width: 100%; height: 100%;">
		<table width="50%" height = "70%" align="center"  style = "margin:0; margin-left: 25%; margin-top: 1%; border-collapse: collapse;">
				<tr id = "comm_head">	
					<td colspan="2" align="center">가족 그룹 생성</td>
				</tr>
				<tr >
					<td width="20%" class = "titles">가족명</td>
					<td width="80%" class = "form">
						<input type="text" name="name" required="required" value = "${idKey}의 가족" />
					</td>
				</tr>
				<tr class = "form">
					<td class = "titles">가족 설명</td>
					<td class = "form"><textarea rows="20" cols="70" name="memo"></textarea></td>
				</tr>
				<tr>
					<td colspan = "2" align = "right">
						<input style = "margin: 0; margin-right: 10px"class = "myButton" type="submit" id = "confirm" value="가족 생성"/>
					</td>
				</tr>
		</table>
		<input type = "hidden" name = "host" value = "${idKey}"/>
		</div>
		</form>

</body>
</html>