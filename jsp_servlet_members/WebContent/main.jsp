<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:if test = "${empty loginUser }"> <!--  해당 세션이 비어있을 경우 -->
	 <jsp:forward page = "login.do"></jsp:forward>
</c:if>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "logout.do">
		<table>
			<tr>
				<td>안녕하세요~! ${loginUser.getName() } (${loginUser.getUserid() }) 님</td>
			</tr>
			<tr>
				<td>
					<input type = "submit" value = "로그아웃" />
					<input type = "button" value = "회원 정보 수정" onclick = "location.href ='memberUpdate.do?userid=${loginUser.getUserid()}'"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>