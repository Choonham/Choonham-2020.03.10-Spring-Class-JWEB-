package com.choonham.mpd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.FriendDAO;


@WebServlet("/friend_search.do")
public class FriendSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String me = (String)session.getAttribute("idKey");
		String nickName = request.getParameter("nickname");
		FriendDAO dao = new FriendDAO();
		ArrayList<String> already = dao.getFriends(me);
		ArrayList<String> resultList = dao.searchFriend(nickName, me, already);
		request.setAttribute("result", resultList);

		RequestDispatcher rd =  request.getRequestDispatcher("friendMgr/addFriend.jsp?search=d");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
