package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import board.command.BoardCommand;
import board.dao.BoardDAO;

//	AbstractCommandController 를 상속받은 이유 : 
//	ModelAndView handle~~() 의 매개 변수가 Controller interface와 다르기 때문
/*
AbstractCommandController의 콜백메서드인 handle() 가 전달받는 객체
 handle() 콜백메서드의 매개변수와 설정 값
1. Request : 요청객체
2. Response : 응답객체
3. Object : 입력받은 값을 저장하는 객체
4. BindException : 사용자로부터 값을 입력 시, 에러가 발생하면 처리해주는 class
*/
public class WriteActionController extends AbstractCommandController {

	BoardDAO dao; // BoardDAO dao = new BoardDAO();

	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("WriteActionController setDao()호출됨(dao) : " + dao);
	}
	
	// 매개변수를 알기 쉽게 변환
	@Override
	protected ModelAndView handle(HttpServletRequest request, 
												  HttpServletResponse response, 
												  Object command, 
												  BindException error) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		//	spring 방식
		//	BoardCommnad 는 상속받은 부모 class(AbstractCommandController)의 
		// "commandClass" 속성에 의해 자동 전달(주입).
        BoardCommand data = (BoardCommand)command;
        dao.write(data);
/*        
        //	기존 Model2(MVC) 방식
        String author = request.getParameter("author");
        String content =request.getParameter("content");
        String title = request.getParameter("title");
        dao.write(author, title, content);
*/
        
        

        // 기존 Model2(MVC) 방식
//        response.sendRedirect("list.jsp");
        
        //	spring 방식
		return new ModelAndView("redirect:/list.do"); 	
	}
}






/*
System.out.println("spring 방식");
//ModelAndView mav = new ModelAndView();
//mav.setViewName("list"); 		

// 또는
ModelAndView mav = new ModelAndView("list");
// 위에 ModelAndView() 안에 넣음으로서  
// mav.setViewName("list"); 생략이 가능하다.
System.out.println("spring 방식 return mav");
return mav;
//	
// 또는 
//위의 주석 문을 한줄로 처리 가능하다
*/