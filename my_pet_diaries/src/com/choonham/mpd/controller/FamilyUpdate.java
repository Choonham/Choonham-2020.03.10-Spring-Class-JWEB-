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

/**
 * Servlet implementation class FamilyUpdate
 */
@WebServlet("/family_info_update.do")
public class FamilyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("idKey");
		FamilyDAO dao = new FamilyDAO();
		int stat = (int)session.getAttribute("status");
		if(stat == 1) {
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('가입 승인이 완료되지 않았습니다.');");
			out.print( "location.href = 'family_group.do';");
			out.print("</script>");
		} else {
			if(flag.equals("walk")) {
				boolean result = dao.updateWalk(id);
				if(result) {
					response.sendRedirect("family_group.do");
				}
			} else if(flag.equals("treat")) {
				boolean result = dao.updateTreat(id);
				if(result) {
					response.sendRedirect("family_group.do");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
