package com.choonham.mpd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.choonham.mpd.dto.MemberDTO;

public class MemberDAO {

	public MemberDAO() {
		// TODO Auto-generated constructor stub
	}
	
	// 중복 확인
	public int confirmID(String userid) {
		int result=-1;
		
		ConnectionDAO dao = ConnectionDAO.getInstance();
		String sql  = "SELECT ID FROM MEMBER  WHERE ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //아이디가 중복된다.
				result = 1;
			} else {
				result = -1; //아이디가 중복되지 않는다
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				dao.freeConnection(conn, pstmt);		
			} catch(Exception e) {
				System.err.println();
			}
		}
		return result;
	}
	
	//회원가입
	public int join(MemberDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		ConnectionDAO dao = ConnectionDAO.getInstance();
		try {
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getNickName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getPetName());
			pstmt.setInt(7, dto.getHasFamily());
			pstmt.setString(8, dto.getFamilyName());
			
			int r = pstmt.executeUpdate();
			
			if(r > 0) {
				result = 1;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		
		return result;
	}

	// 로그인
	public int login(String id, String pwd) {
		int result  = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		String sql = "SELECT PWD FROM MEMBER WHERE ID = ?";
		try {
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
					result = 1;
				} else result = 0;  // 비밀번호가 틀림
			} else {
				result = -1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return result;
	}
	
	// 반려동물 이름 가져오기
	public String getPetName(String id) {
		String petName = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		String sql = "SELECT PETNAME FROM MEMBER WHERE ID = ?";
		try {
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				petName = rs.getString(1);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return petName;
	}
	
	public MemberDTO getMemberInfo(String id) {
		MemberDTO dto = new MemberDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		String sql = null;
		
		try {
			conn = dao.getConnection();
			sql = "SELECT * FROM MEMBER WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setNickName(rs.getString("NICKNAME"));
				dto.setPwd(rs.getString("PWD"));
				dto.setPetName(rs.getString("PETNAME"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setPhone(rs.getString("PHONE"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		
		return dto;
	}
	
	public boolean updateInfo(MemberDTO dto) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		String sql = null;
		
		try {
			conn = dao.getConnection();
			sql = "UPDATE MEMBER SET PWD = ?, NICKNAME = ?, PETNAME = ?, EMAIL = ?, PHONE = ? WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getNickName());
			pstmt.setString(3,  dto.getPetName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6,  dto.getId());
			
			int r = pstmt.executeUpdate();
			
			if(r > 0) result = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
}
