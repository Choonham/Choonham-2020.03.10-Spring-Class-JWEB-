package com.choonham.mpd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.FamilyDAO;

/**
 * Servlet implementation class FamilyDelete
 */
@WebServlet("/family_delete.do")
public class FamilyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String host = request.getParameter("host");
		FamilyDAO dao = new FamilyDAO();

		boolean result = dao.removeFamily(host);
		if(result) {
			response.sendRedirect("family_group.do");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
