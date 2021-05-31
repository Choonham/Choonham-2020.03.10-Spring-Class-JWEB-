package com.choonham.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.dao.BoardDAO;
import com.choonham.dto.BoardVO;

public class BoardListAction implements Action {

	public BoardListAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardList.jsp";
		
		BoardDAO dao = BoardDAO.getInstance();
		
		List<BoardVO> boardList = dao.selectAllBoards();
		
		request.setAttribute("boardList", boardList);
		
		/**RequestDispatcher 객체를 통한 포워딩**/
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
