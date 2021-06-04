<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<c:if test ="${sessionScope.idKey eq null }">
	<script>
				alert("로그인 후 이용 가능합니다.");
				location.href = "/memberMgr/login.jsp";
	</script>
</c:if>

<c:if test ="${sessionScope.idKey ne null }">
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>메시지 창</title>
	<script src = "js/script.js"></script>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
</head>
<body>
	<%@ include file="../main/top.jsp" %>
	<br />
	<form action = "msg_delete.do">
		<table width="85%" align="center" bgcolor="#000000">
			<tr >
				<th bgcolor = "white">        
				  	<table  width="90%" border="1" align="center">
				  		<tr bordercolor="#f5f5f5" >
				  			<td colspan = "6" align="center" ><font size = "40">쪽지함</font></td>
				  		</tr>
				  		<tr align="center">
				  			<td width = "5%">
				  				<input type = "checkbox" name = "checkAll" value = "all" onclick = "checkAll()"/>
				  				<!--  자바 스크립트로 체크했을 때 전체 선택할 수 있게끔 구현 -->
				  			</td>
				  			<td width = "5%">No</td>
				 			<td width = "10%">receiver</td>
				 			<td width = "60%">content</td>
				 			<td width = "10%">Date</td>
				 			<td width = "10%">sender</td>
				  		</tr>
				  		<c:forEach var = "i" items = "${requestScope.msgList }">
				  			<tr>
					  			<td>
					  				<input type = "checkbox" name = "checkMsg" value = "${i.getCode() }"/>
					  			</td>
					  			<td>${i.getCode() }</td>
					  			<td>${i.getTo() }</td>
					  			<td><a onclick = "javascript:msgView('${i.getCode() }', '${i.getTo() }', '${i.getContent() }', '${i.getTime() }', '${i.getFrom() }' )"><b>${i.getContent() }</b></a></td>
					  			<td>${i.getTime() }</td>
					  			<td>${i.getFrom() }</td>
				  			</tr>
				  		</c:forEach>
			
				  		<tr align = "right">
				  			<td colspan="6">
				  				<input type = "button" value ="쪽지 보내기" onclick="msgSend('${requestScope.friend }', '${sessionScope.idKey}')"/>
				  			</td>
				  		</tr>
				  	</table>
				</th>
			</tr>
		</table>
	</form>
</body>
</html>
</c:if>