<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<c:if test ="${sessionScope.idKey eq null }">
	<script>
				alert("로그인 후 이용 가능합니다.");
				location.href = "community.jsp";
	</script>
</c:if>

<c:if test ="${sessionScope.idKey ne null }">
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>다이어리 글 쓰기 페이지</title>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
	<link rel="stylesheet" href="/my_pet_diaries/css/diaryWrite.css">
</head>
<body>
<%@ include file="../main/top.jsp" %>
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
		<div id = "form">
		<table width="60%" align="center">
			<tr>
				<th>        
					<form action = "../diary_write.do" method="post" enctype="multipart/form-data">
					  	<table id = "write_form" width="90%" align="center" border="1">
					  		<tr>
					  			<td colspan = "3" align="center" ><font size = "30">Write a day</font></td>
					  		</tr>
					  		<tr>
					  			<td width = "10%" align = "center">Title</td>
					  			<td width = "80%" align = "left"><input type = "text" size = "100%" required = "required" name = "title"/></td>
					  			<td width = "10%">
					  				<select name="isPublic" id="isPublic">
									    <option value="publicAll">전체 공개</option>
									    <option value="publicRel">팔로우 공개</option>
									    <option value="publicNot">비공개</option>
									  </select>
					  			</td>
					  		</tr>
					  		<tr>
					  			<td width = "10%" align = "center">Content</td>
					  			<td width = "90%" align = "left" colspan = "2"><textarea rows="30" cols="100" name="content" required="required"></textarea></td>
					  		</tr>
					  		<tr>
					  			<td width = "10%" align = "center">Writer</td>
					  			<td width = "90%" align = "left" colspan = "2"><input type = "text" required = "required" width = "100%" name = "writer" readonly = "readonly" value = "${sessionScope.idKey }"/></td>
					  		</tr>
					  		<tr>
					  			<td width = "10%" align = "center">file</td>
					  			<td width = "90%" align = "left" colspan = "2"><input type = "file"  name = "files" multiple="multiple"/></td>
					  		</tr>
					  		<tr align = "right">
					  			<td colspan="3">
					  				<input type = "submit" id = "button" value ="저장"/>
					  			</td>
					  		</tr>
					  	</table>
				  	</form>
				</th>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>
</c:if>