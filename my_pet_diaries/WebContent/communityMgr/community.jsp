<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>커뮤니티 메인 페이지</title>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
	<link rel="stylesheet" href="/my_pet_diaries/css/community_css.css">
</head>
<body>
	<%@ include file="../main/top.jsp" %>   
	<div>
	<aside>
		<p id = "main">Most Liked Posts</p>
		<table width = "100%">
			<tr align = "center"  id = "most_like">
				<th width = "70%">title</th>
				<th width = "20%">writer</th>
				<th width = "10%">likes</th>
			</tr>
			<c:if test = "${requestScope.mostLikes.size() ne 0 }">
				<c:forEach var = "rank" items = "${requestScope.mostLikes }">
					<tr align = "center" >
						<td><a href = "/my_pet_diaries/comm_detail.do?no=${rank.getNo() }">${rank.getTitle() }</a></td>
						<td>${rank.getWriter() }</td>
						<td>${rank.getLikes() }</td>
					<tr>
				</c:forEach>
			</c:if>
		</table>
	</aside>
	  	<table  id = "posts" width="60%" align="left">
	  		<tr >
	  			<td colspan = "6" align="center" ><font size = "40">Community</font></td>
	  		</tr>
	  		<tr id = "comm_head" align="center">
	  			<td width = "5%">No</td>
	 			<td width = "50%">Title</td>
	 			<td width = "20%">Writer</td>
	 			<td width = "15%">Date</td>
	 			<td width = "5%">Hits</td>
	 			<td width = "5%">Likes</td>
	  		</tr>
	  		<c:forEach var = "i" items = "${requestScope.commList }">
	  			<tr id = "rows" align = "center">
		  			<td>${i.getNo() }</td>
		  			<td><a href = "/my_pet_diaries/comm_detail.do?no=${i.getNo() }">${i.getTitle() }</a></td>
		  			<td>${i.getWriter() }</td>
		  			<td>${i.getWdate() }</td>
		  			<td>${i.getHits() }</td>
		  			<td>${i.getLikes() }</td>
	  			</tr>
	  		</c:forEach>

	  		<tr align = "right">
	  			<td colspan="6">
	  				<a class = "myButton" href = "/my_pet_diaries/writeComm_proc.do">글쓰기</a>
	  			</td>
	  		</tr>
	  	</table>
	 </div>
</body>
</html>