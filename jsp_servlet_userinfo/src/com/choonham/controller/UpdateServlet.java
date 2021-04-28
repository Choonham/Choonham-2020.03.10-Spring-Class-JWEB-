package com.choonham.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.dao.UserInfoDAO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		UserInfoDAO dao = UserInfoDAO.getInstance();

		int result = dao.update(pwd, name);

		if (result == 1)
			response.sendRedirect("UserInfoInitForm.jsp");
		else
			System.out.println("워우!! 수정 못해쒀열...");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
