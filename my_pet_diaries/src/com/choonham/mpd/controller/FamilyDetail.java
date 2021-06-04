package com.choonham.mpd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.FamilyDAO;
import com.choonham.mpd.dto.FamilyDTO;

/**
 * Servlet implementation class FamilyDetail
 */
@WebServlet("/family_group.do")
public class FamilyDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		FamilyDAO dao = new FamilyDAO();
		
		FamilyDTO dto = dao.getFamilyInfo((String)session.getAttribute("idKey"));
		ArrayList<FamilyDTO> members = dao.getFamilyMembers((String)session.getAttribute("idKey"));
		
		if(members.size() == 0) {
			session.setAttribute("noFam", null);
		} else {
			session.setAttribute("noFam", "false");
			session.setAttribute("hostID", dto.getHostID());
			session.setAttribute("familyName", dto.getFamilyName());
			session.setAttribute("petName", dto.getPetName());
			session.setAttribute("members", members);
			session.setAttribute("status", dto.getStatus());
			session.setAttribute("lww", dto.getLastWalkWith());
			session.setAttribute("lwt", dto.getLastWalkTime());
			session.setAttribute("ltt", dto.getLastTreat());
			session.setAttribute("who", dto.getWhoGive());
			session.setAttribute("memo", dto.getMemo());
		}
		
		response.sendRedirect("familyMgr/familyGroup.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
