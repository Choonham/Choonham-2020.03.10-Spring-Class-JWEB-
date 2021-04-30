<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form action = "login.do" method = "post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type = "text" name = "userid" value = "${userid }" required = "required"/></td>
			</tr>
			
			<tr>
				<td>비밀번호</td>
				<td><input type = "password" name = "pwd" required = "required"/></td>
			</tr>
			
			<tr>
				<td colspan = "2" align = "center">
					<input type = "submit" value = "로그인" />
					<input type = "reset" value = "취소" />
					<input type = "button" value = "회원가입" onclick = "document.location.href = 'join.do'"/>
				</td>
			</tr>
			
			<tr>
				<td colspan = "2">${message }</td>
			</tr>
			
		</table>
	</form>
</body>
</html>