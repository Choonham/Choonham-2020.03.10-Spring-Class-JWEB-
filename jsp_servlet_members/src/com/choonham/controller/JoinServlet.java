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
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//단순한 회원가입 응답 처리
		RequestDispatcher rd = request.getRequestDispatcher("member/join.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//실제 회원가입 기능 수행
		request.setCharacterEncoding("UTF-8");
		
		MemberDTO dto = new MemberDTO();
		
		dto.setName(request.getParameter("name"));
		dto.setUserid(request.getParameter("userid"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setEmail(request.getParameter("email"));
		dto.setPhone(request.getParameter("phone"));
		dto.setAdmin(request.getParameter("admin"));
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int result = dao.insertMember(dto);
		
		HttpSession session = request.getSession();
		
		if(result == 1) {
			session.setAttribute("userid", dto.getUserid()); //로그인 화면에서 아이디를 바로 출력하기 위함
			session.setAttribute("message", "회원가입에 성공했습니다.");
		} else  {
			session.setAttribute("message",  "회원가입에 실패했어유...");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("member/login.jsp");
		rd.forward(request, response);
		
	}

}
