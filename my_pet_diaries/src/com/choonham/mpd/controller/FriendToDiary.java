package com.choonham.mpd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.FriendDAO;


@WebServlet("/friend_diary.do")
public class FriendToDiary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String diaryHost = request.getParameter("diaryHost");
		FriendDAO dao = new FriendDAO();
		String id = dao.getID(diaryHost);
		
		response.sendRedirect("diary_list.do?diaryHost=" + id);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
