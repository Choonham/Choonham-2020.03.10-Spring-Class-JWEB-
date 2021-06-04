package com.choonham.mpd.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.FamilyDAO;


@WebServlet("/family_accept.do")
public class FamilyAccept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("member");
		FamilyDAO dao = new FamilyDAO();
		boolean result = dao.acceptMember(id);
		
		if(result) {
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('승인처리 되었습니다.')");
			out.print("</script>");
			response.sendRedirect("family_group.do");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
