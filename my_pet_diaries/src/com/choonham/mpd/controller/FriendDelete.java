package com.choonham.mpd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.FriendDAO;


@WebServlet("/friend_delete.do")
public class FriendDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("idKey");
		String friend = request.getParameter("friend");
		FriendDAO dao = new FriendDAO();
		String friendID = dao.getID(friend);
		boolean result = dao.removeFriend(id, friendID);
		 
		if(result) response.sendRedirect("friend_list.do");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
