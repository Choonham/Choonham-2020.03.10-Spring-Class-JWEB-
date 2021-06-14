package board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class SearchActionController implements Controller {

	BoardDAO dao; // BoardDAO dao = new BoardDAO();

	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao()호출됨(dao) : " + dao);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,  HttpServletResponse response) throws Exception {

        System.out.println("SearchActionController 실행됨!");
        
        String searchName=request.getParameter("searchName");
        String searchValue=request.getParameter("searchValue");
        
        ArrayList<BoardDTO> list = dao.search(searchName,searchValue);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("list"); 	//	list.jsp
        
        //	request.setAttribute("list",list);
        mav.addObject("list", list); 	
        
        return mav;
	}

}
