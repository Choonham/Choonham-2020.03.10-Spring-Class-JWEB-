<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>가족 그룹 찾기</title>
	<script src = "../js/searchFamily.js"></script>
	<link rel="stylesheet" href="/my_pet_diaries/css/saerchFriend.css">
</head> 
<body>
	<form name = "searchForm" method = "post">
		<div id = "title">
			<p class="font" align="center">가족 찾기</p>
		</div>
		
		<div id = "inputNick">
			<c:if test = "${param.search eq 'f'}">
				<p class="font" align="center">찾고자하는 가족의 Host ID를 입력해주세요.</p>
			</c:if>
			<div id = "searchBar">
				<input id = "bar" type = "text" name = "searchHost"/>
			</div>
			<div style="width:100%; text-align: center;">
				<input class = "myButton" id = "searchBtn" type = "button" onclick="javascript: loadSearch()" value = "검색"/>
			</div>
			<c:if test = "${param.search eq 's'}">
				<%
					RequestDispatcher rd = request.getRequestDispatcher("../search_family.do");
					rd.forward(request, response);
				%>
			</c:if>
		</div>
		<c:if test = "${param.search eq 'd'}">
			<c:if test = "${requestScope.result.size() ne 0 }">
				<ul>
					<c:forEach var = "i" begin = "0" end = "${requestScope.result.size() -1 }" step = "1">
						<li style = "margin: 0; margin-bottom: 5px; padding: 5px;">
							<div style = "width: 100%;">
								<div style="float: left; margin: 0; vertical-align: top;">
								<p class = "font">
									${requestScope.result.get(i).getFamilyName() }
									&nbsp; 
									${requestScope.result.get(i).getHostID() }
									&nbsp;
								</p>
								</div>
								<div style="float: right;">
									<button type = "button" class = "myButton" onclick = "location.href='../add_member.do?host=${requestScope.result.get(i).getHostID()}&name=${requestScope.result.get(i).getFamilyName() }'">
									가입 신청
									</button>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test = "${requestScope.result.size() eq 0 }">
				<p class="font">해당 가족을 찾을 수 없네요...</p>
			</c:if>
		</c:if>
		<div style="width:100%; margin-top: 100px; text-align: right;"><a href = "#" id="closeBtn" onclick="self.close()" class = "myButton">닫기</a></div>
		<input type = "hidden" name = "search" value = "s"/>
	</form>
</body>
</html>