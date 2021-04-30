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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/memberUpdate.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = null;
		
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");
		String userid = request.getParameter("userid");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		dto.setPwd(pwd);
		dto.setEmail(email);
		dto.setPhone(phone);
		dto.setAdmin(admin);
		dto.setUserid(userid);
		
		int result = dao.updateMember(dto);
		
		url = "main.jsp";
		
		if(result == 1)  {
			dto = dao.getMember(userid);
			session.setAttribute("loginUser", dto);
			session.setAttribute("message", "회원 정보 수정에 성공했습니다.");
		}
		else {
			session.setAttribute("message", "회원 정보 수정에 실패했습니다.");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
