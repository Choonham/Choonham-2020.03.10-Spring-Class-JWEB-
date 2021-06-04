package com.choonham.mpd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.choonham.mpd.dto.FriendDTO;

public class FriendDAO {
	
	ConnectionDAO dao = ConnectionDAO.getInstance();
	
	public FriendDAO() {
		// TODO Auto-generated constructor stub
	}

	//친구 목록 추출
	public ArrayList<String> getFriends(String host) {
		ArrayList<String> resultList = new ArrayList<>();
		ArrayList<FriendDTO> friendList = new ArrayList<FriendDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		HashMap<String, String> nickMap = this.mapNicks();
		try {
			conn = dao.getConnection();
			sql = "SELECT * FROM FRIENDS WHERE (FRIEND_HOST = ?) OR (FRIEND = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, host);
			pstmt.setString(2, host);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FriendDTO dto = new FriendDTO();
				dto.setHost(rs.getString(1));
				dto.setMember(rs.getString(2));
				friendList.add(dto);
			}
			
			for(int i = 0; i < friendList.size(); i++) {
				if(!friendList.get(i).getHost().equals(host)) {
					resultList.add(nickMap.get(friendList.get(i).getHost()));
				} else {
					resultList.add(nickMap.get(friendList.get(i).getMember()));
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		
		return resultList;
	}
	
	//친구 검색
	public ArrayList<String> searchFriend(String nickName, String me, ArrayList<String> already) {
		ArrayList<String> resultList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = dao.getConnection();
			sql = "SELECT NICKNAME, ID FROM MEMBER WHERE NICKNAME LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  "%"+nickName+"%");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String temp1 = rs.getString(1);
				String temp2 = rs.getString(2);
				if((!temp2.equals(me))&&(!already.contains(temp2)))
					
					resultList.add(temp1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		
		return resultList;
	}
	
	// 닉네임으로 아이디 추출
	public String getID(String nickName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String result = null;
		try {
			conn = dao.getConnection();
			sql = "SELECT ID FROM MEMBER WHERE NICKNAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  nickName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		
		return result;
	}
	
	//아이디 - 닉네임 맵핑
	public HashMap<String, String> mapNicks() {
		HashMap<String, String> nickMap = new HashMap<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String result = null;
		try {
			conn = dao.getConnection();
			sql = "SELECT ID, NICKNAME FROM MEMBER";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				nickMap.put(rs.getString(1), rs.getString(2));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		
		return nickMap;
	}
	
	//친구 추가
	public boolean addFriend(FriendDTO dto) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "INSERT INTO FRIENDS VALUES (?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  dto.getHost());
			pstmt.setString(2,  dto.getMember());
			int r = pstmt.executeUpdate();
			
			if(r > 0) result = true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		
		return result;
	}
	
	//친구 삭제
	public boolean removeFriend(String member1, String member2) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = dao.getConnection();
			sql = "DELETE FROM FRIENDS WHERE (FRIEND_HOST = ? AND FRIEND = ?) OR (FRIEND_HOST = ? AND FRIEND = ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,  member1);
		pstmt.setString(2,  member2);
		pstmt.setString(3,  member2);
		pstmt.setString(4,  member1);
		
		int r = pstmt.executeUpdate();
		
		if(r>0) result = true;
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		
		return result;
	}
}
