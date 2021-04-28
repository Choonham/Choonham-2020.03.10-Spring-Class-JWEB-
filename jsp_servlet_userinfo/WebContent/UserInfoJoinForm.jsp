<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>UserInfoJoinForm</title>
</head>
<body>
	<h3>회원가입</h3>
		<form action = "insert">
			이름: <input type = "text" name = "name" required = "required"/> <br />
			아이디: <input type = "text" name = "id" required = "required"/>  <br />
			비밀번호: <input type = "password" name = "pwd" required = "required"/> <br />
			<input type = "submit" value = "저장" />
		</form>
</body>
</html>