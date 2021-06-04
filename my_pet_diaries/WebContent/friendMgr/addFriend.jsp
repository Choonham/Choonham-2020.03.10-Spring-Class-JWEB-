<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>친구 검색 & 추가 페이지</title>
	<script src = "../js/script.js"></script>
	<link rel="stylesheet" href="/my_pet_diaries/css/saerchFriend.css">
</head>
<body>
	<form action = "addFriend.jsp" method = "get" name = "frm"> 
	<div id = "title">
		<p class="font" align="center">친구 찾기</p>
	</div>
	
	<div id = "inputNick">
		<c:if test = "${param.search eq 'f'}">
			<p class="font" align="center">찾고자하는 친구의 닉네임을 입력해주세요.</p>
		</c:if>
		<div id = "searchBar">
			<input id = "bar" type = "text" name = "nickname"/>
		</div>
		<div style="width:100%; text-align: center;"><input class = "myButton" id = "searchBtn" type = "submit" value = "친구 찾기"/></div>
		<input type = "hidden" name = "search" value = "s"/>
		<c:if test = "${param.search eq 's'}">
		<%
			RequestDispatcher rd = request.getRequestDispatcher("../friend_search.do");
			rd.forward(request, response);
		%>
		</c:if>
	</div>
		<c:if test = "${param.search eq 'd'}">
			<c:if test = "${requestScope.result.size() ne 0 }">
			<ul>
			<c:forEach var = "i" begin = "0" end = "${requestScope.result.size() -1 }" step = "1">
				<li>
					<div style = "width: 100%;">
						<div style="float: left; margin: 0; vertical-align: top;"><p class = "font">${requestScope.result.get(i) }</p></div>
						<div style="float: right;"><button type = "button" class = "myButton" onclick = "location.href='../friend_add.do?memberNickname=${requestScope.result.get(i)}'">친구 신청</button></div>
						<div style="float: right; margin-right: 3px;">
							<button type = "button" class = "myButton" onclick = "openDiary('${requestScope.result.get(i)}')">Diary</button>
						</div>
					</div>
				</li>
			</c:forEach>
			</ul>
			</c:if>
			<c:if test = "${requestScope.result.size() eq 0 }">
				<p class="font">친구를 찾을 수 없네요...</p>
			</c:if>
		</c:if>
		<div style="width:100%; margin-top: 100px; text-align: right;"><a href = "#" id="closeBtn" onclick="self.close()" class = "myButton">닫기</a></div>
			
	</form>
</body>
</html>