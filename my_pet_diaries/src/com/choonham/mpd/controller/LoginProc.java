package com.choonham.mpd.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.MemberDAO;

/**
 * Servlet implementation class LoginProc
 */
@WebServlet("/login_proc.do")
public class LoginProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("memberMgr/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String url = "memberMgr/login.jsp";
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		int result = dao.login(id,  pwd);
		
		if(result == 1) {
			url = "main/main.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("idKey", id);
			response.sendRedirect(url);
		} else {
			PrintWriter out = response.getWriter();
			String str="";
			str = "<script language='javascript'>";
			str += "alert(\"아이디 혹은 비밀번호를 확인해주세요.\");";
			str += "history.back();";
			str += "</script>";
			out.print(str);
		}
		
		
	}
}
