package com.choonham.mpd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.choonham.mpd.dto.MsgDTO;

public class MessageDAO {

	ConnectionDAO dao = ConnectionDAO.getInstance();
	
	public MessageDAO() {
		
	}
	
	// 메시지 기록 추출
	public ArrayList<MsgDTO> getHistory(String host, String id) {
		ArrayList<MsgDTO> dtoList = new ArrayList<MsgDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "SELECT * FROM MESSAGE WHERE (FROM_WHO = ? AND TO_WHO = ?) OR (FROM_WHO = ? AND TO_WHO = ?) ORDER BY MSG_TIME DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  host);
			pstmt.setString(2,  id);
			pstmt.setString(3,  id);
			pstmt.setString(4, host);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MsgDTO dto = new MsgDTO();
				dto.setCode(rs.getString(1));
				dto.setFrom(rs.getString(2));
				dto.setTime(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setTo(rs.getString(5));
				dtoList.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return dtoList;
	}
	
	
	// 메시지 보내기
	public boolean sendMsg(MsgDTO dto) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "INSERT INTO MESSAGE(MSG_CODE, FROM_WHO, MSG_CONTENT, TO_WHO) VALUES (TO_CHAR(MSG_SEQ.NEXTVAL, 'FM000000'), ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,  dto.getFrom());
			pstmt.setString(2,  dto.getContent());
			pstmt.setString(3, dto.getTo());
			
			int r = pstmt.executeUpdate();
			
			if(r>0) result = true;
			 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	// 메세지 상세
	public MsgDTO getMessage(String code) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MsgDTO dto = new MsgDTO();
		try {
			conn = dao.getConnection();
			sql = "SELECT * FROM MESSAGE WHERE MSG_CODE = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setCode(rs.getString(1));
				dto.setFrom(rs.getString(2));
				dto.setTime(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setTo(rs.getString(5));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return dto;
	}


}
