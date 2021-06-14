package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import board.command.BoardCommand;
import board.dao.BoardDAO;

public class UpdateActionController extends AbstractCommandController {
	
	BoardDAO dao; 
	//	BoardDAO dao = new BoardDAO();

    
    public void setDao(BoardDAO dao) {
        this.dao = dao;
        System.out.println("setDao()호출됨(dao) : "+dao);
    }
    
	//	board-servlet.xml 의
    //	<property name="commandClass" value="board.command.BoardCommand"/>
    
    //	AbstractCommandController 가 가지고 있는 객체
    //	1. Request 요청객체
    //	2. Response 응답객체
    //	3. Object 입력받은 값을 저장하는 객체
    //	4. BindException 사용자로부터 값을 입력시 에러가 발생하면 처리해주는 class
	@Override
	protected ModelAndView handle(HttpServletRequest request, 
											 HttpServletResponse response, 
											 Object command, 
											 BindException error) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
/*		
		//BoardCommnad 는 상속받은 부모의 class이다.
        BoardCommand data = (BoardCommand)command; 
        
        String author = data.getAuthor();
        String content =data.getContent();
        String title = data.getTitle();
        String num = request.getParameter("num");
        dao.update(num, author, title, content); 
*/ 
        BoardCommand data = (BoardCommand)command;
        String num = request.getParameter("num");
    	dao.update(data, num);

 
        return new ModelAndView("redirect:/list.do");
	}

}



