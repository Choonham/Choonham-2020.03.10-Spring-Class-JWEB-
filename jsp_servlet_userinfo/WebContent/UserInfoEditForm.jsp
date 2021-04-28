<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>UserInfoEditForm</title>
</head>
<body>
	<form action = "update">
		이름: <input type = "text" name = "name" value = "${sessionScope.DTO.getName() }" readonly = "readonly"/> <br />
		아이디: <input type = "text" name = "id" value = "${sessionScope.DTO.getId() }" readonly = "readonly"/>  <br />
		비밀번호: <input type = "text" name = "pwd" value = "${sessionScope.DTO.getPwd() }" /> <br />
		<input type = "submit" value = "저장" />
	</form>
</body>
</html>