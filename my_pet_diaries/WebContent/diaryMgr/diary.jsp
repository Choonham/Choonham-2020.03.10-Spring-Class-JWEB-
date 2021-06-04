<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn"%>
<%
	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n");
%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>다이어리 페이지</title>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
	<link rel="stylesheet" href="/my_pet_diaries/css/diary_css.css">
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src = "../js/getLike.js"></script>
</head>
<body>
	<%@ include file="../main/top.jsp" %>
	<input type = "hidden" class = "param" value = "${param.i }"/>
	<div id = "wrapper">
		<div id = "pictures">
			<p id = "pictureTitle">My days with you</p>
			<c:forEach var = "coll" items = "${sessionScope.fileMap.values() }" >
				<c:forEach var = "list" items = "${coll }">
					<div id ="photos">
						<img src = "/my_pet_diaries/imgs/${list}" style = "border-radius: 5%; margin: 0; width: 100%; height: 100%"/>
					</div>
				</c:forEach>
			</c:forEach>
		</div>
		<div id = "diary">
			<form action = "#">
				<table id = "posts"> 
				<c:if test = "${sessionScope.list.size() eq 0 }">
					<tr>
						<th>
							<div id = "no_post">There's no post... let's make a good one together!</div>
						</th>
					</tr>
				</c:if>
				<c:set var = "done_loop" value = "false"/>
		  		<c:if test = "${sessionScope.list.size() ne 0 }">
			  			<tr id = "head"> 
							<th id = "head_date">Date</th>
							<th id = "head_title">Title</th>
							<th id = "head_like">Like</th>
							<th id = "head_edit">Edit</th>
						</tr>
					<c:forEach var="i" begin = "${param.i }" end = "${param.i +2}">
						
						<c:if test = "${done_loop ne true}">
							<tr>
								<td id = "td_date"><input type = "text" name = "date" class = "date_input" readonly = "readonly" value = "${sessionScope.list.get(i).getWdate()}" /></td>
					  			<td id = "td_title"><input type = "text" name = "title" class = "title_input" readonly = "readonly" value = "${sessionScope.list.get(i).getTitle() }" /></td>
					  			<td id = "td_like">
					  				<div id = "divv" style= "padding: 0; vertical-align: center; margin: 0; margin-left: 30%;">
						  				<div onmouseout = "closeLayer()" style = "float: left; vertical-align: center; margin: 0; height: 100%">
						  					<input type = "text" name = "likes" class = "like_input" readonly = "readonly" value = "${sessionScope.list.get(i).getLikes() }"/>
							  			</div>		
							  			<div style = "float: left; vertical-align: center; margin: 0; padding: 0; padding-top: 3px; height: 100%">
							  				<img  class = "edit_img" src="https://img.icons8.com/material-sharp/24/000000/like--v1.png" onclick = "location.href='../diary_like.do?code=${sessionScope.list.get(i).getCode() }&writer=${sessionScope.list.get(i).getWriter() }'"/>
							  			</div>	
						  			</div>
					  			</td>
					  			<td id = "td_edit">
					  				<c:if test = "${param.isHost eq true }">
					  				<div style= "margin: 0; margin-right: 30%;">
					  					<img class = "edit_img" src="https://img.icons8.com/metro/26/000000/delete-sign.png" onclick = "location.href='../delete_diary.do?code=${sessionScope.list.get(i).getCode() }'"/>
					  					<img class = "edit_img" src="https://img.icons8.com/metro/26/000000/edit.png" onclick = "location.href='updateDiary.jsp?index=${i}'"/>
					  				</div>
					  				</c:if>
					  			</td>
					  		</tr>
					  		<div class = "likeList" id = "like_list${i }">
							<p>추천한 사람</p>
							<c:forEach var = "j" items = "${sessionScope.likeMap.get(sessionScope.list.get(i).getCode()) }">
								<p>${j }</p>
							</c:forEach>
				  		</div>
					  		<tr>
					  			<td colspan = "4">
					  				<div id = "content">
					  					<c:set var ="c" value = "${sessionScope.list.get(i).getCode() }"/>
					  					<c:if test = "${sessionScope.fileMap.get(c) ne null}">
					  						<c:forEach var="files" items="${sessionScope.fileMap.get(c) }">
					  							<img src = "/my_pet_diaries/imgs/${files}" style = "margin: 0; width: 400px; height: 300px"/>
					  						</c:forEach>
					  					</c:if>
					  					<div id = "line">
				  						</div>
					  						${fn:replace(sessionScope.list.get(i).getContent(), cn, br) }
					  				</div>
					  			</td>
					  		</tr>
					  			<!--  Writer: <input type = "text" name = "writer" readonly = "readonly" value = "${sessionScope.list.get(i).getWriter() }" />-->
				  			<tr>
				  				<td colspan = "3">
				  					<div id = "line">
				  					</div>
				  				</td>
				  			</tr>
				  		</c:if>
				  		<c:if test = "${i == sessionScope.list.size()-1 }">
				  			<c:set var="done_loop" value="true"/>
				  		</c:if>
				  		
					</c:forEach>
				</c:if>
						<tr>
							<td colspan = "3" align="center" id = "numbers">
								<c:forEach var = "i" begin = "1" end = "${ sessionScope.list.size() }" step = "3">
									<c:set var = "index" value = "${i - (2*(i/3)) }"/>
									<a href = "diary.jsp?i=${i-1 }&isHost=${param.isHost}">
										<fmt:formatNumber value = "${index+(1-(index%1))%1 }" type = "number"/>
									</a>
								</c:forEach>
							</td>
						</tr>
						<c:if test = "${param.isHost eq true }">
							<tr>
								<td colspan = "3" align = "right">
			  						<button type = "button" id = "wirteButton"onclick="location.href='../diary_write.do'">글쓰기</button>
			  					</td>
			  				</tr>
		  				</c:if>
		  				</table>
		  			</form>
		</div>
	</div>
	
</body>
</html>