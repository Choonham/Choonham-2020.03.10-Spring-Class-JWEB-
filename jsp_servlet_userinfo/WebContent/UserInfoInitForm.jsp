<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>UserInfoInitForm</title>
</head>
<body>
	<h3>성함을 입력하십시오.</h3>
	<form action="search">
		이름: <input type="text"  name="name" size="5" maxlength="5" required="required" />
		<input type="submit" value="검색" />
		<input type = "button" value = "회원가입" onclick = "location.href = 'UserInfoJoinForm.jsp'"/>
	</form>
</body>
</html>