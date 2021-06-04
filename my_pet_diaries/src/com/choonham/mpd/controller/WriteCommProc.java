package com.choonham.mpd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.CommunityDAO;
import com.choonham.mpd.dto.CommunityDTO;


@WebServlet("/writeComm_proc.do")
public class WriteCommProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityDAO dao = new CommunityDAO();
		ArrayList<CommunityDTO> mostLikes = dao.getMostLikes();
		request.setAttribute("mostLikes", mostLikes);
		RequestDispatcher rd = request.getRequestDispatcher("/communityMgr/writeComm.jsp");
		rd.forward(request, response);
		//response.sendRedirect("communityMgr/writeComm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		/*
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("id");
		
		CommunityDTO dto = new CommunityDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		*/
		CommunityDAO dao = new CommunityDAO();
		
		int result = dao.write(request);
		
		if(result == 1) {
			response.sendRedirect("comm_list.do");
		} else {
			System.out.print("뭐야 돌려줘요");
		}
	}

}
