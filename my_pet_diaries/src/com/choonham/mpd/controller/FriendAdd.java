package com.choonham.mpd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.FriendDAO;
import com.choonham.mpd.dto.FriendDTO;


@WebServlet("/friend_add.do")
public class FriendAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String memberNick = request.getParameter("memberNickname");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("idKey");
		FriendDTO dto = new FriendDTO();
		FriendDAO dao = new FriendDAO();
		dto.setHost(id);
		dto.setMember(dao.getID(memberNick));
		ArrayList<String> list = dao.getFriends(id);
		
		if(list.contains(dao.getID(memberNick))) {
			PrintWriter out = response.getWriter();
			String str="";
			str = "<script language='javascript'>";
			str += "alert(\"이미 친구인 계정입니다!\");";
			str += "</script>";
			out.print(str);
		} else { 
			boolean result = dao.addFriend(dto);
			if(result) {
				PrintWriter out = response.getWriter();
				String str="";
				str = "<script language='javascript'>";
				str += "alert(\"추가했습니다!\");";
				str += "opener.window.location.href = 'friend_list.do';";  //오프너 새로고침
				str += "self.close();";   // 창닫기
				str += "</script>";
				out.print(str);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
