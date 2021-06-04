package com.choonham.mpd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.MemberDAO;
import com.choonham.mpd.dto.MemberDTO;

/**
 * Servlet implementation class JoinProc
 */
@WebServlet("/join_proc.do")
public class JoinProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("memberMgr/join.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		MemberDTO dto = new MemberDTO();

		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setNickName(request.getParameter("nickname"));
		dto.setPetName(request.getParameter("petname"));
		dto.setEmail(request.getParameter("email"));
		dto.setPhone(request.getParameter("phone"));
		dto.setHasFamily(0);
		dto.setFamilyName(null);

		 MemberDAO join = new MemberDAO();

		int result = join.join(dto);

		if (result == 1) {
			response.sendRedirect("memberMgr/login.jsp");
		} else {
			response.sendRedirect("memberMgr/join.jsp");
		}
	}
}
