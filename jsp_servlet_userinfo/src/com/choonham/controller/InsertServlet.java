package com.choonham.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.dao.UserInfoDAO;
import com.choonham.dto.JoinDTO;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		JoinDTO dto = new JoinDTO();

		dto.setName(name);
		dto.setId(id);
		dto.setPwd(pwd);

		UserInfoDAO dao = UserInfoDAO.getInstance();
		int result = dao.join(dto);

		if (result == 1)
			response.sendRedirect("UserInfoInitForm.jsp");
		else
			System.out.print("워우! 에러 발생!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
