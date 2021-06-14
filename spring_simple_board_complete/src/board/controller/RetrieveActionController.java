package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;


public class RetrieveActionController implements Controller {

	BoardDAO dao; // BoardDAO dao = new BoardDAO();

	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao()호출됨(dao) : " + dao);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("RetrieveActionController 실행됨!");
		String num = request.getParameter("num");
		System.out.println("num : " + num);

		// 레코드 한 개를 담을 객체 필요
		BoardDTO data = dao.retrieve(num); // 검색

		ModelAndView mav = new ModelAndView("retrieve2");
//		 mav.setViewName("retrieve"); //retrieve.jsp

		// request.setAttribute("data",data);
		// 데이터 메모리에 올려서 다른 페이지에도 이어지게 하기
		mav.addObject("data", data);

		return mav; // 메모리에 올림
	}

}
