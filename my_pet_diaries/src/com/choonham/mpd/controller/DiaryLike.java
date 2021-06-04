package com.choonham.mpd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.DiaryDAO;

@WebServlet("/diary_like.do")
public class DiaryLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		String writer = request.getParameter("writer");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("idKey");
		DiaryDAO dao = new DiaryDAO();
		
		HashMap<String, ArrayList<String>> likeMap = null;
		likeMap = dao.getLikes(writer, 0);
		
		ArrayList<String> list = likeMap.get(code);
			
		if(list != null) {
			if(list.contains(id)) {
					PrintWriter out = response.getWriter();
					String str="";
					str = "<script language='javascript'>";
					str += "alert(\"이미 추천했습니다!\");";
					str += "location.href = \"diary_list.do?diaryHost=" + writer + "\"";
					str += "</script>";
					out.print(str);
			}
		}

		if(list == null || !list.contains(id)) {
			boolean result = dao.pushLike(0, code, id, writer);
			
			if(result) {
				PrintWriter out = response.getWriter();
				String str="";
				str = "<script language='javascript'>";
				str += "alert(\"♥\");";
				str += "location.href = \"diary_list.do?diaryHost=" + writer + "\"";
				str += "</script>";
				out.print(str);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
  