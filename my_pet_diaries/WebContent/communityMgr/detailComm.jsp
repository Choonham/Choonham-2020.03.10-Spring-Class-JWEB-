<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn"%>

<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>${requestScope.dto.getTitle() }</title>
	<script src = "/my_pet_diaries/main/script.js"></script>
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
			<form action = "/my_pet_diaries/modifyComm_proc.do" method = "get">
				<input type = "hidden" name = "no" value = "${requestScope.dto.getNo() }"/>
				<input type = "hidden" name = "flag" value = "update"/>
				<div style = "margin:0; margin-left: 7%; float :left">
			  	<table  width="55%" style = "margin: 0; margin-top: 2%; border-collapse: collapse;">
			  		<tr id = "comm_head">
			  			<td width = "85%" align = "center"><input class = "input_text"  type = "text" name = "title" readonly = "readonly" value = "${requestScope.dto.getTitle() }" style = "text-align:center" /></td>
			  			<td width = "20px" align = "left">hit<input class = "input_text"  type = "text" name = "hit" readonly = "readonly" value = "${requestScope.dto.getHits() }" /></td>
			  			<td width = "20px" align = "left">like<input class = "input_text"  type = "text" name = "likes" readonly = "readonly" value = "${requestScope.dto.getLikes() }" /></td>
			  		</tr>
			  		<tr>
			  			<td colspan = "3" align = "left" class = "fileForm">첨부파일: <a href = "file_download.do?fileName=${requestScope.dto.getFileName() }">${requestScope.dto.getFileName() }</a></td>
			  		</tr>
			  		<tr>
			  			<td colspan = "4" width = "100%" align = "right" class = "form"><input type = "text" algin = "right" class = "input_text"  name = "writer" readonly = "readonly" value = "${requestScope.dto.getWriter() }" style = "text-align:right"/></td>
			  		</tr>
			  		<tr>
			  			<td colspan = "4" width = "100%" align = "center" class = "form">
			  				<textarea rows="30" cols="110" name="content" required="required" readonly = "readonly" style = "padding-top: 20px; padding: 0">${requestScope.dto.getContent() }</textarea></td>
			  		</tr>
			  		<c:if test = "${sessionScope.idKey eq requestScope.dto.getWriter() }">
				  		<tr align = "right">
				  			<td colspan="4" class = "form">
				  				<input type = "submit" value ="수정" />
				  				<input type = "button" value ="삭제" onclick="location.href='/my_pet_diaries/modifyComm_proc.do?flag=delete&no=${requestScope.dto.getNo() }'"/>
				  				<input type = "button" value ="추천" onclick="javascript:pushLike(${requestScope.dto.getNo() })"/>
				  			</td>
				  		</tr>
			  		</c:if>
			  		
			  		<c:if test = "${sessionScope.idKey ne requestScope.dto.getWriter() }">
			  		<tr align = "right">
			  			<td colspan="4">
			  				<input type = "button" value ="추천" onclick="javascript:pushLike(${requestScope.dto.getNo() })"/>
			  			</td>
			  		</tr>
			  		</c:if>
			  	</table>
			  	</div>
		  	</form>
</body>
</html>