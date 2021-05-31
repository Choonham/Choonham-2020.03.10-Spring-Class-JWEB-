<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>boardWrite.jsp</title>
	<link rel = "stylesheet" type = "text/css" href = "css/shopping.css">
</head>
<body>
	<div id = "wrap" align = "center">
		<h1>게시글 등록 화면</h1>
		<form action = "BoardServlet" method="post">
				<input type = "hidden" name = "command" value = "board_write" />
				
				<table>
					<tr>
						<th>작성자</th>
						<td>
							<input type = "text" name = "name"/>
						</td>
					</tr>
					
					<tr>
						<th>비밀번호</th>
						<td>
							<input type = "password" name = "pass" required = "required"/>
							* 필수 (게시물 수정, 삭제 시 필요)
						</td>
					</tr>
					
					<tr>
						<th>이메일</th>
						<td>
							<input type = "email" name = "email"/>
						</td>
					</tr>
					
					<tr>
						<th>제목</th>
						<td>
							<input type ="text" name = "title"/>*필수
						</td>
					</tr>
					
					<tr>
						<th>내용</th>
						<td>
							<textarea rows = "15" cols = "40" name = "content"></textarea>
						</td>
					</tr>
				</table>
				<br/><br/>
				
				<input type = "submit" value = "글 등록"/>
				<input type = "reset" value = "다시 작성"/>
				<input type = "button" value = "글 목록" onclick = "location.href = 'BoardServlet?command=board_list'"/>
		</form>
	</div>
</body>
</html>