package com.choonham.mpd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.CommunityDAO;
import com.choonham.mpd.dto.CommunityDTO;

/**
 * Servlet implementation class DetailCommProc
 */
@WebServlet("/modifyComm_proc.do")
public class ModifyComm_proc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    //삭제, 추천
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		String n = request.getParameter("no");
		int no = Integer.parseInt(n);
		CommunityDAO dao = new CommunityDAO();
		if(flag.equals("delete")) {
			dao.delete(no);
			 response.sendRedirect("comm_list.do");
		} else if(flag.equals("like")) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"추천했습니다.\")</script>");
			dao.upLike(no);
			
			ArrayList<CommunityDTO> dtoList = dao.getList();
			HttpSession session = request.getSession();
			session.setAttribute("commList", dtoList);
			
			response.sendRedirect("comm_detail.do?no="+no);
		} else if(flag.equals("update")) {
			ArrayList<CommunityDTO> mostLikes = dao.getMostLikes();

			request.setAttribute("mostLikes", mostLikes);

			RequestDispatcher rd = request.getRequestDispatcher("/communityMgr/updateComm.jsp");
			rd.forward(request, response);
		}
	}

	//수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 
		 /*
		 String title = request.getParameter("title");
		 String content = request.getParameter("content");
		 CommunityDTO dto = new CommunityDTO();
		 dto.setTitle(title);
		 dto.setContent(content);
		 dto.setNo(no);
		 */
		 
		 CommunityDAO dao = new CommunityDAO();
		 int no = dao.update(request);
		
		 response.sendRedirect("comm_detail.do?no="+no);
	}

}
