package com.choonham.mpd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.FamilyDAO;
import com.choonham.mpd.dao.MemberDAO;
import com.choonham.mpd.dto.FamilyDTO;

/**
 * Servlet implementation class FamilyMake
 */
@WebServlet("/make_family.do")
public class FamilyMake extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("familyMgr/makeFamilyGroup.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String host = request.getParameter("host");
		String name = request.getParameter("name");
		String memo = request.getParameter("memo");
		
		MemberDAO member = new MemberDAO();
		FamilyDTO dto = new FamilyDTO();
		dto.setHostID(host);
		dto.setFamilyName(name);
		dto.setMemberID(host);
		dto.setPetName(member.getPetName(host));
		dto.setMemo(memo);
		
		FamilyDAO dao = new FamilyDAO();
		boolean result = dao.makeFamilyGroup(dto);;
		
		if(result) {
			dao.updateFamilyInfo(host);
		}
		response.sendRedirect("family_group.do");
		
	}

}
