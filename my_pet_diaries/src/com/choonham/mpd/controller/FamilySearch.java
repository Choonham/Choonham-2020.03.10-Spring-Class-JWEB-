package com.choonham.mpd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.FamilyDAO;
import com.choonham.mpd.dto.FamilyDTO;

/**
 * Servlet implementation class FamilySearch
 */
@WebServlet("/search_family.do")
public class FamilySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchWord = request.getParameter("searchHost");
		
		FamilyDAO dao = new FamilyDAO();
		ArrayList<FamilyDTO> resultList = new ArrayList<FamilyDTO>();
		resultList = dao.searchFamilyGroup(searchWord);
		
		request.setAttribute("result", resultList);
		RequestDispatcher rd = request.getRequestDispatcher("familyMgr/searchFamilyGroup.jsp?search=d");
		rd.forward(request, response);
		
	}

}
