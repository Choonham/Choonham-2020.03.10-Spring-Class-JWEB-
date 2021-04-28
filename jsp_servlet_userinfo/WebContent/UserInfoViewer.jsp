<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>UserInfoViewer</title>
</head>
<body>
	이름: ${sessionScope.DTO.getName() } <br />
	아이디: ${sessionScope.DTO.getId() } <br />
	비밀번호: ${sessionScope.DTO.getPwd() } <br />
	<input type = "button" value = "수정" onclick = "location.href = 'UserInfoEditForm.jsp'"/>
</body>
</html>