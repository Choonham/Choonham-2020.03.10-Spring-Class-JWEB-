<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 정보 수정</h1>
	<form action = "memberUpdate.do" method = "post" name = "frm">
		<table>
	
			<tr>
				<td>이름</td>
				<td><input type = "text" name = "name" required = "required" size = "20" value = "${loginUser.getName() }" readonly = "readonly"/></td>
			</tr>
			
			<tr>
				<td>아이디</td>
				<td>
					<input type = "text" name = "userid" required = "required" value = "${loginUser.getUserid() }" size = "20" readonly = "readonly"/>
				</td>
			</tr>
			
			<tr>
				<td>비밀번호</td>
				<td><input type = "password" name = "pwd"  required = "required" size = "20"  value = "${loginUser.getPwd() }"/>*</td>
			</tr>
			
			<tr>
				<td>비밀번호 확인</td>
				<td><input type = "password" name = "pwd_check"  required = "required" size = "20" value = "${loginUser.getPwd() }"/>*</td>
			</tr>
			
			<tr>
				<td>이메일</td>
				<td><input type = "email" name = "email"  required = "required" size = "20" value = "${loginUser.getEmail() }"/></td>
			</tr>
			
			<tr>
				<td>전화번호</td>
				<td><input type = "tel" name = "phone"  required = "required" size = "20" value = "${loginUser.getPhone() }"/></td>
			</tr>
			
			<tr>
				<td>
					<c:if test = "${loginUser.getAdmin() == 0 }">
						<input type = "radio" name = "admin" value = "0" checked = "checked"/> 일반 회원
						<input type = "radio" name = "admin" value = "1" /> 관리자
					</c:if>
					<c:if test = "${loginUser.getAdmin() == 1 }">
						<input type = "radio" name = "admin" value = "0" /> 일반 회원
						<input type = "radio" name = "admin" value = "1" checked = "checked"/> 관리자
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td colspan = "2" align = "center">
					<input type = "submit" value = "회원정보 수정" onclick = "return joinCheck()"/>
					<input type = "reset" value = "취소" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>