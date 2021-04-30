package com.choonham.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.choonham.dto.MemberDTO;

public class MemberDAO {
	
	private static MemberDAO instance = null;
	
	public MemberDAO() {
		// TODO Auto-generated constructor stub
	}
	//MemberDAO 내부 메서드들
	public static MemberDAO getInstance() {
		//instance가 이미 존재할 때는 절대 새로운 인스턴스를 생성 불가
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}

	// Connection 객체
	public Connection getConnection(){
		Connection conn=null;
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String userid = "choonham";
		String userpwd = "6725";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userid, userpwd);
		} catch(Exception e) {
			System.err.println();
		}
		
		return conn;
	}

	// 사용자 인증시 사용하는 메소드
	public int userCheck(String userid, String pwd) {
		//아이디 오류 :  -1, 비밀번호 오류 : 0, 성공 : 1
		int result=-1;
		
		String sql  = "SELECT PWD FROM MEMBER_TBL  WHERE USERID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //해당 아이디가 존재할 경우
				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) { //비밀번호가 동일함
					result = 1;
				} else result = 0;  // 비밀번호가 틀림
			} else { //해당 아이디가 존재하지 않을 경우
				result = -1;
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				System.err.println();
			}
		}
		
		return result;
	}

	// 아이디로 회원 정보 가져오는 메소드
	public MemberDTO getMember(String userid) {
		MemberDTO dto=null;
		
		String sql = "SELECT * FROM MEMBER_TBL WHERE USERID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setName(rs.getString("name"));
				dto.setUserid(rs.getString("userid"));
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setAdmin(rs.getString("admin"));
			}
		} catch(Exception e) {
			System.err.println();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				System.err.println();
			}
		}
		
		return dto;
	}

	// 아이디 중복 확인 메서드
	public int confirmID(String userid) {
		int result=-1;
		
		String sql  = "SELECT USERID FROM MEMBER_TBL  WHERE USERID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
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
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				System.err.println();
			}
		}
		return result;
	}

	// 회원 가입 메서드
	public int insertMember(MemberDTO dto) {
		int result=-1;
		
		String sql  = "INSERT INTO MEMBER_TBL VALUES(MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  dto.getName());
			pstmt.setString(2,  dto.getUserid());
			pstmt.setString(3,  dto.getPwd());
			pstmt.setString(4,  dto.getEmail());
			pstmt.setString(5,  dto.getPhone());
			pstmt.setString(6,  dto.getAdmin());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				System.err.println();
			}
		}
		return result;
	}

	// 회원 정보 수정 메서드
	public int updateMember(MemberDTO dto) {
		int result=-1;
		
		String sql  = "UPDATE MEMBER_TBL SET PWD = ?, EMAIL = ?, PHONE = ?, ADMIN = ? WHERE USERID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  dto.getPwd());
			pstmt.setString(2,  dto.getEmail());
			pstmt.setString(3,  dto.getPhone());
			pstmt.setString(4,  dto.getAdmin());
			pstmt.setString(5,  dto.getUserid());
			
			int r = pstmt.executeUpdate();
			if(r>0) result = 1;
			else result = -1;
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				System.err.println();
			}
		}
		
		return result;
	}
}
