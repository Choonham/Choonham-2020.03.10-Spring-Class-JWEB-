<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<c:if test ="${sessionScope.idKey eq null }">
	<script>
				alert("로그인 후 이용 가능합니다.");
				location.href = "comm_list.do";
	</script>
</c:if>

<c:if test ="${sessionScope.idKey ne null }">
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>커뮤니티 글 쓰기 페이지</title>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
	<link rel="stylesheet" href="/my_pet_diaries/css/writeForm.css">
</head>
<body>
	<%@ include file="../main/top.jsp" %>
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
			<form action = "writeComm_proc.do" method="post" enctype="multipart/form-data">
			  	<table  width="55%" align="left" style = "margin:0; margin-left: 5%; margin-top: 1%; border-collapse: collapse;">
			  		<tr id = "comm_head">
			  			<td colspan = "2" align ="center" ><font size = "40">글 작성</font></td>
			  		</tr>
			  		<tr>
			  			<td width = "10%" align = "center" class = "titles">Title</td>
			  			<td width = "90%" align = "left" class = "form"><input type = "text" required = "required" name = "title" size = "100%"/></td>
			  		</tr>
			  		<tr>
			  			<td width = "10%" align = "center" class = "titles">Content</td>
			  			<td width = "90%" align = "left" class = "form"><textarea rows="30" cols="150" name="content" required="required"></textarea></td>
			  		</tr>
			  		<tr>
			  			<td width = "10%" align = "center" class = "titles">Writer</td>
			  			<td width = "90%" align = "left" class = "form"><input type = "text" required = "required" name = "id" readonly = "readonly" value = "${sessionScope.idKey }"/></td>
			  		</tr>
			  		<tr>
			  			<td width = "10%" align = "center" class = "titles">file</td>
			  			<td width = "90%" align = "left" class = "form"><input type = "file"  name = "files" multiple="multiple"/></td>
			  		</tr>
			  		<tr align = "right" class = "form">
			  			<td colspan="2">
			  				<input class = "myButton" type = "submit" value ="저장"/>
			  			</td>
			  		</tr>
			  	</table>
		  	</form>

</body>
</html>
</c:if>