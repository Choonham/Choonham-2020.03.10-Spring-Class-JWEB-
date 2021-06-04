<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>메시지 창</title>
	<link rel="stylesheet" href="/my_pet_diaries/css/msg.css">
</head>
<body>
<br />
		<form action = "/my_pet_diaries/msg_send.do" method = "post">
			<table width="450" height = "280"  align="center" bgcolor="#000000">
				<tr >
					<th bgcolor = "white">
					  	<table  width = "90%" border = "1" align = "center">
					  		<tr bordercolor="#f5f5f5">
					  			<td colspan = "2" align="center" ><font size = "20">message</font></td>
					  		</tr>
					  		<tr align="right">
					  			<td width = "30%">받는 사람:</td>
					 			<td width = "70%">
					 				<input type = "text" name = "to" value = "${param.to }" readonly = "readonly"/>
					 			</td>
					  		</tr>
					  		<tr align="right">
					 			<td colspan = "2">
					 				<textarea rows="15" cols="80" name="content" required="required"></textarea>
					 			</td>
					  		</tr>
					  		<tr align="right">
					  			<td width = "30%">보낸 사람:</td>
					 			<td width = "70%">
					 				<input type = "text" name = "from" value = "${param.from }" readonly = "readonly"/>
					 			</td>
					  		</tr>
							<tr align = "right">
				  			<td colspan="2">
				  				<input type = "submit" value ="보내기"/>
				  			</td>
				  		</tr>
					  	</table>
					</th>
				</tr>
			</table>
		</form>
</body>
</html>