package com.choonham.mpd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.MemberDAO;

/**
 * Servlet implementation class IdCheck
 */
@WebServlet("/idcheck.do")
public class IdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.confirmID(id);
		
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher("memberMgr/idCheck.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
