package com.choonham.mpd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.FriendDAO;

/**
 * Servlet implementation class FriendList
 */
@WebServlet("/friend_list.do")
public class FriendList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("idKey");
		String friend = request.getParameter("friend");
		FriendDAO friDao = new FriendDAO();
		String friId = friDao.getID(friend);
		FriendDAO dao = new FriendDAO();
		ArrayList<String> friends =  dao.getFriends(id);
		session.setAttribute("list", friends);
		
		if(request.getParameter("friend")==null) {
			response.sendRedirect("friendMgr/friendList.jsp");
		} else {
			response.sendRedirect("friendMgr/friendList.jsp?friend=" + friId);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
