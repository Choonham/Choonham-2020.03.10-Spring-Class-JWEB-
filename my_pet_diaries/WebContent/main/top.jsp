<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<div id = "container">
	<div id = "logo">
		<img src = "/my_pet_diaries/img/icons8-dog-heart-64.png" width = "80px" height = "80px" align = "middle" onclick="location.href = '/my_pet_diaries/main/main.jsp'"/>
	</div>
	<div id = "banner">
		<header>
			My pet diary
		</header>	
		<nav>
			<div id = "menu">
				<ul>
					<li>
						<button class = "nav_btn" id = "nav_btn0" type ="button" onclick="location.href='/my_pet_diaries/diary_list.do?i=0'">Diary</button>
					</li>
					<li>
						<button class = "nav_btn" id = "nav_btn1" type ="button" onclick="location.href='/my_pet_diaries/family_group.do'">Family</button>
					</li>
					<li>
						<button class = "nav_btn" id = "nav_btn2" type ="button" onclick="location.href='/my_pet_diaries/friend_list.do'">Friend</button>
					</li>
					<li>
					<button class = "nav_btn" id = "nav_btn3" type ="button" onclick="location.href='/my_pet_diaries/comm_list.do'">Community</button>
					</li>
				</ul>
			</div>
			<c:if test = "${sessionScope.idKey eq null }">
			<div id = "memberMenu">
				<ul>
					<li>
						<button class = "nav_btn" id = "nav_btn4" type=button onclick = "location.href = '/my_pet_diaries/login_proc.do'">Sign in</button>
					</li>
					<li>
						<button class = "nav_btn" id = "nav_btn5" type=button onclick = "location.href = '/my_pet_diaries/join_proc.do'">Sign up</button>
					</li>
				</ul>
			</div>
			</c:if>
			<c:if test = "${sessionScope.idKey ne null }">
			<div  id = "memberMenu">
				<ul>
					<li>
						<button class = "nav_btn" id = "nav_btn4" type=button onclick = "location.href = '/my_pet_diaries/update_info.do'">My Info</button>
					</li>
					<li>
						<button class = "nav_btn" id = "nav_btn5" type=button onclick = "location.href = '/my_pet_diaries/logout_proc.do'">Logout</button>
					</li>
				</ul>
			</div>
				<!--  <a href = memberMgr/updateInfo.jsp> 회원수정 </a>-->
			</c:if>
			
		</nav>
	</div>
</div>
	