<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src = "script/member.js"></script>
</head>
<body>
	<h1>회원 가입</h1>
	<h3>'*' 은 필수 입력사항입니다.</h3>
	<form action = "join.do" method = "post" name = "frm">
		<table>
			<tr>
				<td>이름</td>
				<td><input type = "text" name = "name" required = "required" size = "20"/>*</td>
			</tr>
			
			<tr>
				<td>아이디</td>
				<td>
					<input type = "text" name = "userid" required = "required" size = "20"/>
					<input type = "hidden" name = "reid" size = "20" />
					<input type = "button" value = "중복 체크" onclick = "idCheck()" />
				</td>
			</tr>
			
			<tr>
				<td>비밀번호</td>
				<td><input type = "password" name = "pwd"  required = "required" size = "20"/>*</td>
			</tr>
			
			<tr>
				<td>비밀번호 확인</td>
				<td><input type = "password" name = "pwd_check"  required = "required" size = "20"/>*</td>
			</tr>
			
			<tr>
				<td>이메일</td>
				<td><input type = "email" name = "email"  required = "required" size = "20"/></td>
			</tr>
			
			<tr>
				<td>전화번호</td>
				<td><input type = "tel" name = "phone"  required = "required" size = "20"/></td>
			</tr>
			
			<tr>
				<td>
					<input type = "radio" name = "admin" value = "0" checked = "checked"/> 일반 회원
					<input type = "radio" name = "admin" value = "1" /> 관리자
				</td>
			</tr>
			
			<tr>
				<td colspan = "2" align = "center">
					<input type = "submit" value = "가입" onclick = "return joinCheck()"/>
					<input type = "reset" value = "취소" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>