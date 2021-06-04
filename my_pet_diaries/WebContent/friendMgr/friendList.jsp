<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>친구 목록 페이지</title>
	<script src = "../js/script.js"></script>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
	<link rel="stylesheet" href="/my_pet_diaries/css/friend_css.css">
</head>
<body>
	<%@ include file="../main/top.jsp" %>
	<div class = "friendMain">
		<c:if test ="${param.friend eq null }">
			<img src = "/my_pet_diaries/img/friends-icon-png-2.jpg"/>
		</c:if>
		<c:if test = "${param.friend ne null }">
			<%@ include file="../message/messageView2.jsp" %>
		</c:if>
	</div>
	<aside>
		<h3 style = "margin: 0; margin-top: 20px;" align = "center">Friends List</h3>
		<ul>
			<c:if test =  "${sessionScope.list.size() ne 0 }">
			<c:forEach var = "i" begin = "0" end = "${sessionScope.list.size()-1 }">
				<li class = "list">
					<a style = " font-size: 1.2em; margin: 0;" href = "../friend_diary.do?diaryHost=${sessionScope.list.get(i) }">${sessionScope.list.get(i) }</a>
					<button type ="button" class = "button" onclick="location.href='../friend_delete.do?friend=${sessionScope.list.get(i) }'">친구 삭제</button>
					<button type = "button" class = "button" onclick = "location.href='../friend_list.do?friend=${sessionScope.list.get(i) }'">메시지함</button>
				</li>
			</c:forEach>
			</c:if>
		</ul>
		<a class = "myButton" id = "add_button" href = "javascript: friendSearch()" >친구 추가</a>
		<!-- <button type ="button" class = "button" id = "add_button" onclick="friendSearch()">친구 추가</button> -->
	</aside>
</body>
</html>