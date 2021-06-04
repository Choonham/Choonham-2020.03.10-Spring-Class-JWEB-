package com.choonham.mpd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.DiaryDAO;
import com.choonham.mpd.dto.DiaryDTO;


@WebServlet("/diary_write.do")
public class DiaryWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("diaryMgr/writeDiary.jsp"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiaryDAO dao = new DiaryDAO();
		
		boolean result = dao.writeDiary(request);
		
		if(result) {
			response.sendRedirect("diary_list.do");
		}
		
	}

}
