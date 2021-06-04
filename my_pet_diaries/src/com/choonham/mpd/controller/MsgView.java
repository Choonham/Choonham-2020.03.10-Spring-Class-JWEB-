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
import com.choonham.mpd.dao.MessageDAO;
import com.choonham.mpd.dto.MsgDTO;

@WebServlet("/msg_view.do")
public class MsgView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String host = (String)session.getAttribute("idKey");
		String friend = request.getParameter("friend");
		FriendDAO friDao = new FriendDAO();
		String friId = friDao.getID(friend);
		MessageDAO dao = new MessageDAO();
		ArrayList<MsgDTO> msgList = dao.getHistory(host, friId);
		request.setAttribute("msgList", msgList);
		request.setAttribute("friend", friId);
		RequestDispatcher rd = request.getRequestDispatcher("/message/messageView.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
