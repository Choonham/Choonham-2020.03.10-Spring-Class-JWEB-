package com.choonham.mpd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.DiaryDAO;

@WebServlet("/delete_diary.do")
public class DiaryDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		;
		DiaryDAO dao = new DiaryDAO();
		boolean result = dao.deleteDiary(code);
		
		System.out.print(result);
		
		if(result) response.sendRedirect("diary_list.do");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
