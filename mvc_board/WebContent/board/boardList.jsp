<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>

<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>boardList.jsp</title>
	<link rel = "stylesheet" type = "text/css" href = "css/shopping.css">
</head>
<body>
	<div id = "wrap" align = "center">
		<h1>게시글 리스트</h1>
		<table class = "list">
			<tr>
				<td colspan = "5" style="border: white; text-align: right;">
					<a href = "BoardServlet?command=board_write_form">게시글 등록</a>
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach items = "${boardList }" var = "board">
				<tr class = "record">
					<td>${board.getNum() }</td>
					<td>
						<a href = "BoardServlet?command=board_view&num=${board.getNum() }">
							${board.getTitle() }
						</a>
					</td>
					<td>${board.getName() }</td>
					<td>${board.getWritedate() }</td>
					<td>${board.getReadcount() }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>