package com.choonham.mpd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.CommunityDAO;
import com.choonham.mpd.dto.CommunityDTO;

/**
 * Servlet implementation class CommunityList
 */
@WebServlet("/comm_list.do")
public class CommunityList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityDAO dao = new CommunityDAO();
		ArrayList<CommunityDTO> dtoList = dao.getList();
		ArrayList<CommunityDTO> mostLikes = dao.getMostLikes();

		request.setAttribute("commList", dtoList);
		request.setAttribute("mostLikes", mostLikes);

		RequestDispatcher rd = request.getRequestDispatcher("/communityMgr/community.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
