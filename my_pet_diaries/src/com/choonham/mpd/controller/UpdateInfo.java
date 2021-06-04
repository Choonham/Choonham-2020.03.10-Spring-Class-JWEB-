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
import com.choonham.mpd.dto.MemberDTO;


@WebServlet("/update_info.do")
public class UpdateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("idKey");
		MemberDTO dto = dao.getMemberInfo(id);
		session.setAttribute("info", dto);
		
		response.sendRedirect("memberMgr/myInfo.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("idKey");
		String pwd = request.getParameter("pwd");
		String nickName = request.getParameter("nickname");
		String petName = request.getParameter("petname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setNickName(nickName);
		dto.setPetName(petName);
		dto.setEmail(email);
		dto.setPhone(phone);
		
		boolean result = dao.updateInfo(dto);
		
		if(result) {
			PrintWriter out = response.getWriter();
			String str="";
			str = "<script language='javascript'>";
			str += "alert(\"회원정보가 수정되었습니다.\");";
			str += "location.href = 'main/main.jsp';";
			str += "</script>";
			out.print(str);
		}
		
	}

}
