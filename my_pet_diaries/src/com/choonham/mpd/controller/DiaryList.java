package com.choonham.mpd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choonham.mpd.dao.DiaryDAO;
import com.choonham.mpd.dao.FamilyDAO;
import com.choonham.mpd.dao.FriendDAO;
import com.choonham.mpd.dto.DiaryDTO;
import com.choonham.mpd.dto.FamilyDTO;


@WebServlet("/diary_list.do")
public class DiaryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB에서 데이터를 받아 diary.jsp 쪽으로 이동
		String diaryHost = request.getParameter("diaryHost");
		boolean isHost = false;
		
		DiaryDAO dao = new DiaryDAO();
		FamilyDAO famDao = new FamilyDAO();
		FriendDAO friDao = new FriendDAO();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("idKey");
		ArrayList<DiaryDTO> list = null;
		
		HashMap<String, ArrayList<String>> fileMap = null;
		HashMap<String, ArrayList<String>> likeMap = null;
		
		ArrayList<FamilyDTO> famList = famDao.getFamilyMembers(id);
		ArrayList<String> familyList = new ArrayList<String>();
		
		for(FamilyDTO famDto:famList) {
			familyList.add(famDto.getMemberID());
		}
		
		ArrayList<String> friendList =  friDao.getFriends(id);
		
		if(diaryHost == null || diaryHost.equals(id)) {
			list = dao.getMyList(id, 0);
			fileMap = dao.getImg(id, 0);
			likeMap = dao.getLikes(id, 0);
			isHost = true;
		} else {
			if(familyList.contains(diaryHost)||friendList.contains(diaryHost)) {
				list = dao.getMyList(diaryHost, 1);
				fileMap = dao.getImg(diaryHost, 1);
			} else {
				list = dao.getMyList(diaryHost, 2);
				fileMap = dao.getImg(diaryHost, 2);
			}
			likeMap = dao.getLikes(diaryHost, 0);
			if(diaryHost.equals(id)) isHost = true;
		}
		
		session.setAttribute("likeMap", likeMap);
		session.setAttribute("list", list);
		session.setAttribute("fileMap", fileMap);
		
		response.sendRedirect("diaryMgr/diary.jsp?i=0&isHost="+isHost);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
