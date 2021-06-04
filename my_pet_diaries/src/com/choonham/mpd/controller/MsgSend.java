package com.choonham.mpd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choonham.mpd.dao.FriendDAO;
import com.choonham.mpd.dao.MessageDAO;
import com.choonham.mpd.dto.MsgDTO;


@WebServlet("/msg_send.do")
public class MsgSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String to = request.getParameter("to");
		String from = request.getParameter("from");
		String content = request.getParameter("content");
		
		FriendDAO friDao = new FriendDAO();
		HashMap<String, String> map = friDao.mapNicks();
		
		String friend = map.get(to);
		
		MsgDTO dto = new MsgDTO();
		dto.setTo(to);
		dto.setFrom(from);
		dto.setContent(content);
		
		MessageDAO dao = new MessageDAO();
		boolean result = dao.sendMsg(dto);
		
		if(result) {
			PrintWriter out = response.getWriter();
			String str="";
			str = "<script language='javascript'>";
			str += "alert(\"전송했습니다!\");";
			str += "opener.window.location.href = 'friend_list.do?friend="+ friend +"';";  //오프너 새로고침
			str += "self.close();";   // 창닫기
			str += "</script>";
			out.print(str);
		}
	}

}
