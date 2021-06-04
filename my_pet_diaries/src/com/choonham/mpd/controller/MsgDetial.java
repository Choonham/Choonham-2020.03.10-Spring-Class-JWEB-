package com.choonham.mpd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.MessageDAO;
import com.choonham.mpd.dto.MsgDTO;

@WebServlet("/msg_detail.do")
public class MsgDetial extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		
		MessageDAO dao = new MessageDAO();
		MsgDTO dto = dao.getMessage(code);
		
		request.setAttribute("msg", dto);
		
		RequestDispatcher rd = request.getRequestDispatcher("message/messageDetail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
