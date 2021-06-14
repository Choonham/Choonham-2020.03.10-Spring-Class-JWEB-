<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="board.dto.*"%>
<%
	BoardDTO data = (BoardDTO) request.getAttribute("data"); // == ${data}
	int num = data.getNum();
	String title = data.getTitle();
	String author = data.getAuthor();
	String content = data.getContent();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>내용보기 및 수정</title>
	</head>
	
	<body>
		<form action="update.do">
			<table>
				<tr>
					<td>번 호</td>
					<td>
						<input type="text" name="num" value="${data.num}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>제 목</td>
					<td><input type="text" name="title" value="${data.title}"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="author" value="${data.author}" /></td>
				</tr>
				<tr>
					<td>내 용</td>
					<td>
						<textarea name="content" rows="5" cols="30">${data.content}</textarea>
					</td>
				</tr>
				<tr>
					<td> <input type="submit" value="수정완료" /></td>
					<td>
						<a href="delete.do?num=${data.num}">삭제</a> &nbsp;&nbsp; 
						<a href="list.do">목록보기</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>


