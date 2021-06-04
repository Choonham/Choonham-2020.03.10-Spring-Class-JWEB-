<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>가족 페이지</title>
	<script src = "../js/script.js"></script>
	<link rel="stylesheet" href="/my_pet_diaries/css/top.css">
	<link rel="stylesheet" href="/my_pet_diaries/css/family_css.css">
</head>
<body>
	<%@ include file="../main/top.jsp" %>
	<c:if test ="${sessionScope.noFam eq null }">
		<aside id = "noFam">
			<div style = "margin: 0; padding-top: 10%; width: 100%; text-align: center">
				<img id = "alone" src="https://img.icons8.com/ios/100/000000/lonely.png"/>
				<div id = "alone_text">
					<p style = "margin: 0; margin-top: 20%;">
					You are not belong to any family group...<br/>
					You can either find one or host the group! 
					</p>
				</div>
			</div>
			<input type ="button" class = "myButton" value = "가족 그룹 생성" onclick="location.href='makeFamilyGroup.jsp'"/>
			<input type ="button" class = "myButton" value = "가족 그룹 찾기" onclick="javascipt: searchFamily()"/>
		</aside>
		<div id = "noFamInfo" >
			<div style = "width: 100%; height: 100%; text-align: center; vertical-align: center; margin: 0;">
				<img  id = "noFamImg" src = "/my_pet_diaries/img/ClipartKey_824956.png"/>
			</div>
		</div>
	</c:if>
	
	<c:if test ="${sessionScope.noFam ne null }">
		<aside id = "memberList">
			<div id = "family_head">
				<p id = "family_name">${familyName }</p>
				<p id ="host_id" align = "right">${hostID } <a class = "myButton" href = "../diary_list.do?diaryHost=${hostID }">diary</a></p>
				<p id = "pet_name" align = "right">${petName }</p>
			</div>
			<div id = "members">
				<h2>members</h2>
				<c:if test = "${sessionScope.members.size() ne 0}">
					<c:forEach var="i" items = "${sessionScope.members }">
						<c:if test = "${i.getStatus() eq 0 }">
							<ul class = "member_list">
								<li>
									${i.getMemberID() }
									&nbsp;
									<c:set var = "host" value = "${hostID }"/>
									<c:if test = "${idKey eq host}">
										<c:if test = "${i.getMemberID() ne host}">
											<a class = "myButton" href = "../family_off.do?member=${i.getMemberID() } ">퇴출</a>
											<a class = "myButton" href = "../diary_list.do?diaryHost=${i.getMemberID() }">diary</a>
										</c:if>
									</c:if>	
									<c:set var = "member" value = "${i.getMemberID() }"/>
									<c:if test = "${idKey ne host}">
										<a class = "myButton" href = "../diary_list.do?diaryHost=${i.getMemberID() }">diary</a>
									</c:if>	
									</li>
								</ul>
							</c:if>
							<c:if test = "${i.getStatus() ne 0 }">
								<h2>Waiting for accept</h2>
								<ul class = "member_list">
									<li>
										${i.getMemberID() }
										&nbsp;
										<c:set var = "host" value = "${hostID }"/>
										<c:set var = "tempMem" value = "${i.getMemberID() }"/>
										<c:choose>
											<c:when test = "${idKey eq host}">
												<button type = "button" class = "myButton" onclick = "location.href = '../family_accept.do?member=${i.getMemberID() }'">승인</button>
												<button class = "myButton" onclick = "location.href = '../family_off.do?member=${i.getMemberID() }'">거절</button>
											</c:when>	
											
											<c:when test = "${idKey eq tempMem}">
												<button type = "button" class = "myButton" onclick = "location.href = '../family_off.do?member=${i.getMemberID() }'">가입 신청 철회</button>
											</c:when>	
										</c:choose>
										</li>
									</ul>
									</c:if>
								</c:forEach>
							</c:if>
						</div>
						<div style = "margin: 0; margin-top: 100px; text-align: right">
							<c:if test = "${idKey eq host}">
								<button class = "myButton" onclick = "location.href = '../family_delete.do?host=${idKey }'">그룹 삭제</button>
							</c:if>
							<c:if test = "${idKey ne host}">
								<button class = "myButton" onclick = "location.href = '../family_off.do?member=${idKey}'">탈퇴</button>
							</c:if>
						</div>
					</aside>
					<div id = "infos">
						<div id = "walk_header">
							<img id = "walk_img" src = "/my_pet_diaries/img/icons8-dog-walking-50.png"/>
							<h4 id = "walk_his">산책 기록</h4>
							<a class = "myButton" href = "../family_info_update.do?flag=walk" style = "float: right;">갱신</a>
						</div>
						<div class = "line"></div>
						<ul class = "info_list">
							<li>
								Last Walking: ${lwt }
							</li>
							<li>
								With: ${lww }
							</li>
						</ul>
						
						<div id = "treat_header">
							<img id = "treat_img" src = "/my_pet_diaries/img/icons8-dog-bone-32.png"/>
							<h4 id = "treat_his">간식 기록</h4>
							<a class = "myButton" href = "../family_info_update.do?flag=treat" style = "float: right;">갱신</a>
						</div>
						<div class = "line"></div>
						<ul class = "info_list">
							<li>
								Last Treat: ${ltt }
							</li>
							<li>
								Who gave: ${who }
							</li>
						</ul>
					</div>
	</c:if>
</body>
</html>