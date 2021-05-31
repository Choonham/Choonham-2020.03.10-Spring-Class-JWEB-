package com.choonham.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.dao.BoardDAO;

public class BoardDeleteAction implements Action {

	public BoardDeleteAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num = request.getParameter("num");
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.deleteBoard(num);
		
		new BoardListAction().execute(request, response);
		
	}

}