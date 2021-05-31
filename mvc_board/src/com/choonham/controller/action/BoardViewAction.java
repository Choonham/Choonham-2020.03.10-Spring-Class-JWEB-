package com.choonham.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.dao.BoardDAO;
import com.choonham.dto.BoardVO;

public class BoardViewAction implements Action {

	public BoardViewAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardView.jsp";
		String num = request.getParameter("num");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		/** 1. 제목 클릭 시, 조회수 증가 **/
		dao.updateReadCount(num);
		
		/** 2. 제목에 대한 글 번호를 이용하여 상세보기 관련 데이터 조회 **/
		BoardVO vo = dao.selectOneBoardByNum(num);
		
		request.setAttribute("board", vo);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
