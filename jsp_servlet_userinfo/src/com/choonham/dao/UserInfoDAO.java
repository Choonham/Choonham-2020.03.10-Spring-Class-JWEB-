package com.choonham.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.choonham.dto.JoinDTO;
import com.choonham.dto.UserInfoDTO;

public class UserInfoDAO {

	private static UserInfoDAO instance = null;

	private UserInfoDAO() {

	}

	public static UserInfoDAO getInstance() {
		if (instance == null) {
			instance = new UserInfoDAO();
		}
		return instance;
	}

	public Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String userid = "choonham";
		String userpwd = "6725";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userid, userpwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/** 검색 **/
	public UserInfoDTO search(String name) {
		UserInfoDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM USERINFO WHERE NAME = ?";

		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new UserInfoDTO();
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	/** 수정 **/
	public int update(String pwd, String name) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int result = 0;

		String sql = "UPDATE USERINFO SET PASSWORD = ? WHERE NAME = ?";

		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			int r = pstmt.executeUpdate();

			if (r > 0)
				result = 1;
			else
				result = -1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/** 입력 **/
	public int join(JoinDTO dto) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int result = 0;

		String sql = "INSERT INTO USERINFO VALUES(?, ?, ?)";

		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPwd());

			int r = pstmt.executeUpdate();

			if (r > 0)
				result = 1;
			else
				result = -1;

		} catch (SQLException e) {
			String code = e.getSQLState();
			//System.out.print(code);
			if(code.equals("23000")) {
				System.out.println("아이디가 이미 존재합니다.");
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
