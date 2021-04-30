package com.choonham.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.dao.MemberDAO;
import com.choonham.dto.MemberDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/login.jsp";
		
		//로그인 한 뒤에는 해당 id 를 세션에 저장하여 꺼낼 것
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			url = "main.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 폼에 의해 호출
		String url = "member/login.jsp";
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.userCheck(userid, pwd);
		
		if(result == 1) {
			
			MemberDTO dto = dao.getMember(userid);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",  dto);
			
			request.setAttribute("message", "로그인 되었습니다.");
			url = "main.jsp"; //로그인 완료시 넘어감
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			
		}else if(result == 0) {
			request.setAttribute("message", "비밀번호를 확인해주세요.");
			url = "member/login.jsp";
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}else {
			request.setAttribute("message", "존재하지 않는 아이디입니다.");
			url = "member/login.jsp";
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

}
