package com.choonham.mpd.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.FamilyDAO;


@WebServlet("/add_member.do")
public class FamilyAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("idKey");
		String host = request.getParameter("host");
		String name = request.getParameter("name");
		FamilyDAO dao = new FamilyDAO();
		
		boolean result = dao.addFamilyMember(id, host, name);
		
		if(result) {
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert(\"가입 신청했습니다!\");"); 
			out.print("self.close();");   // 창닫기
			out.print("opener.window.location.href = 'family_group.do';");
			out.print("</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
