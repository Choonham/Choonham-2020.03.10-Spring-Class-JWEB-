package com.choonham.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.dao.UserInfoDAO;
import com.choonham.dto.UserInfoDTO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		UserInfoDTO dto = null;
		UserInfoDAO dao = UserInfoDAO.getInstance();

		dto = dao.search(name);

		HttpSession session = request.getSession();
		session.setAttribute("DTO", dto);

		RequestDispatcher rd = request.getRequestDispatcher("UserInfoViewer.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
