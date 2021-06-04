package com.choonham.mpd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.choonham.mpd.dto.FamilyDTO;

public class FamilyDAO {

	public FamilyDAO() {
		// TODO Auto-generated constructor stub
	}

	
	// 멤버 퇴출, 탈퇴
	public boolean offMember(String id) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = dao.getConnection();
			sql = "DELETE FROM FAMILY_MEMBERS WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int r = pstmt.executeUpdate();
			if(r>0) result = true;
		
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	//가입 승인
	public boolean acceptMember(String id) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = dao.getConnection();
			sql = "UPDATE FAMILY_MEMBERS SET STATUS = 0 WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int r = pstmt.executeUpdate();
			if(r>0) result = true;
		
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	
	// 가족 구성원 정보 추출
	public ArrayList<FamilyDTO> getFamilyMembers(String id) {
		ArrayList<FamilyDTO> members = new ArrayList<FamilyDTO>();
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "SELECT MEMBER_ID, STATUS FROM FAMILY_MEMBERS WHERE HOST_ID IN (SELECT HOST_ID FROM FAMILY_MEMBERS WHERE MEMBER_ID = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FamilyDTO dto = new FamilyDTO();
				dto.setMemberID(rs.getString(1));
				dto.setStatus(rs.getInt(2));
				members.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return members;
	}
	
	
	// 가족 정보 추출
	public FamilyDTO getFamilyInfo(String id) {
		FamilyDTO dto = new FamilyDTO();
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "SELECT * FROM FAMILY_MEMBERS WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setHostID(rs.getString("HOST_ID"));
				dto.setMemberID(rs.getString("MEMBER_ID"));
				dto.setFamilyName(rs.getString("FAMILY_NAME"));
				dto.setPetName(rs.getString("PET_NAME"));
				dto.setStatus(rs.getInt("STATUS"));
				dto.setLastWalkTime(rs.getString("LASTWALKTIME"));
				dto.setLastWalkWith(rs.getString("LASTWALKWITH"));
				dto.setLastTreat(rs.getString("LASTTREAT"));
				dto.setWhoGive(rs.getString("WHOGIVE"));
				dto.setMemo(rs.getString("MEMO"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return dto;
	}
	
	// 가족 생성 메서드
	public boolean makeFamilyGroup(FamilyDTO dto) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int r = 0;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "INSERT INTO FAMILY_MEMBERS (NO, MEMBER_ID, HOST_ID, FAMILY_NAME, PET_NAME, STATUS, MEMO) VALUES (FAM_SEQ.NEXTVAL ,?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberID());
			pstmt.setString(2, dto.getHostID());
			pstmt.setString(3, dto.getFamilyName());
			pstmt.setString(4, dto.getPetName());
			pstmt.setInt(5, 0);
			pstmt.setString(6, dto.getMemo());
			pstmt.executeUpdate();
			
			result = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	//member 테이블의 가족 정보 변경
	public boolean updateFamilyInfo(String id) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "UPDATE MEMBER SET HASFAMILY = ? WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 1);
			pstmt.setString(2, id);
			
			pstmt.execute();
			
			result = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	// 가족 찾기 메서드
	public ArrayList<FamilyDTO> searchFamilyGroup(String host) {
		
		ArrayList<FamilyDTO> dtoList = new ArrayList<FamilyDTO>();
		ArrayList<String> memberList = new ArrayList<String>();
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "SELECT * FROM (SELECT * FROM family_members WHERE member_id = host_id) WHERE HOST_ID LIKE ? ORDER BY NO";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + host + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FamilyDTO dto = new FamilyDTO();
				dto.setMemberID(rs.getString("MEMBER_ID"));
				dto.setHostID(rs.getString("HOST_ID"));
				dto.setFamilyName(rs.getString("FAMILY_NAME"));
				dto.setPetName(rs.getString("PET_NAME"));
				dtoList.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return dtoList;
	}
	
	// 가족 신청 메서드
	public boolean addFamilyMember(String id, String host, String familyName) {
		boolean result = false;
		int count = 0;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "INSERT INTO FAMILY_MEMBERS (NO, MEMBER_ID, HOST_ID, FAMILY_NAME ,STATUS) VALUES (FAM_SEQ.NEXTVAL, ?, ?, ?,1)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, host);
			pstmt.setString(3, familyName);
			
			int r = pstmt.executeUpdate();
			
			if(r>0) result = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	// 업데이트: 마지막 산책 시간 & 사람
	public boolean updateWalk(String id) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int r = 0;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "UPDATE FAMILY_MEMBERS SET LASTWALKTIME = SYSDATE, LASTWALKWITH = ? WHERE HOST_ID IN (SELECT HOST_ID FROM FAMILY_MEMBERS WHERE MEMBER_ID = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			
			r = pstmt.executeUpdate();
			if(r > 0) result = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	// 업데이트: 마지막 간식 시간 & 사람
	public boolean updateTreat(String id) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int r = 0;
		String sql = null;
		try {
			conn = dao.getConnection();
			sql = "UPDATE FAMILY_MEMBERS SET LASTTREAT= SYSDATE, WHOGIVE = ? WHERE HOST_ID IN (SELECT HOST_ID FROM FAMILY_MEMBERS WHERE MEMBER_ID = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			
			r = pstmt.executeUpdate();
			if(r > 0) result = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	//가족 삭제 메서드
	public boolean removeFamily(String hostID) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = dao.getConnection();
			sql = "DELETE FROM FAMILY_MEMBERS WHERE HOST_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostID);
			
			int r = pstmt.executeUpdate();
			if(r>0) result = true;
		
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}

}
