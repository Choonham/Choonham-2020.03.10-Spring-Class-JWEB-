package com.choonham.mpd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.CommunityDAO;
import com.choonham.mpd.dto.CommunityDTO;

/**
 * Servlet implementation class CommunityDetail
 */
@WebServlet("/comm_detail.do")
public class CommunityDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n = request.getParameter("no");
		int no = Integer.parseInt(n);
		CommunityDAO dao = new CommunityDAO();
		ArrayList<CommunityDTO> mostLikes = dao.getMostLikes();

		int upHit = dao.upHit(no);
		
		CommunityDTO dto = dao.getDetail(no);

		request.setAttribute("dto", dto);
		request.setAttribute("mostLikes", mostLikes);

		RequestDispatcher rd = request.getRequestDispatcher("/communityMgr/detailComm.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
