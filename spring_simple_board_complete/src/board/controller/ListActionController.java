package board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

//Controller를 상속받는 이유 : 요청을 받아서 처리하기 위함.
public class ListActionController implements Controller {
	
    BoardDAO dao; 	//	BoardDAO dao = new BoardDAO();
    
    public void setDao(BoardDAO dao) {
        this.dao = dao;
        System.out.println("setDao()호출됨(dao) : "+dao);
    }

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,  HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ListActionController 실행됨!");
		
        ArrayList<BoardDTO> list = dao.list();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("list"); 	//	list.jsp
        
        //request.setAttribute("list",list);
        mav.addObject("list", list); 	

        return mav;
	}

}


