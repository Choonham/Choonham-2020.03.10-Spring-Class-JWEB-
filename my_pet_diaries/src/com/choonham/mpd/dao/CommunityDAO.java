package com.choonham.mpd.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.choonham.mpd.dto.CommunityDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CommunityDAO {
	
	private static final String UPLOAD = "C:/workspace_jweb/my_pet_diaries/WebContent/files/";
	private static final String ENCTYPE = "UTF-8";
	private static final int MAXSIZE = 10*1024*1024;

	public CommunityDAO() {
	}
	
	public ArrayList<CommunityDTO> getMostLikes() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		
		ArrayList<CommunityDTO> dtoList = new ArrayList<>();
		
		String sql = "SELECT * FROM (SELECT NO, TITLE, WRITER, LIKES, RANK() OVER (ORDER BY LIKES DESC)  FROM community) WHERE ROWNUM <= 5";
		
		try {
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommunityDTO dto = new CommunityDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setLikes(rs.getInt("likes"));	
				dtoList.add(dto);
			}
				
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn,  pstmt, rs);
		}
		
		return dtoList;
	}
	
	public ArrayList<CommunityDTO> getList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		
		ArrayList<CommunityDTO> dtoList = new ArrayList<>();
		
		String sql = "SELECT * FROM COMMUNITY ORDER BY NO DESC";
		try {
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommunityDTO dto = new CommunityDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setWdate(rs.getString("wdate"));
				dto.setHits(rs.getInt("hit"));
				dto.setLikes(rs.getInt("likes"));
				dtoList.add(dto);
			}
				
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn,  pstmt, rs);
		}
		
		return dtoList;
	}
	
	public CommunityDTO getDetail(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2= null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		String sql = null, sql2  = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		CommunityDTO dto = new CommunityDTO();
		
		try {
			sql = "SELECT * FROM COMMUNITY WHERE NO = ?";
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setWdate(rs.getString("wdate"));
				dto.setHits(rs.getInt("hit"));
				dto.setLikes(rs.getInt("likes"));
			}
			
			sql2 = "SELECT FILE_NAME, FILE_ORG FROM FILE_COMMUNITY WHERE CODE = ?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, no);
			System.out.print(no);
			rs2 = pstmt2.executeQuery();
			
			if(rs2.next()) {
				dto.setFileName(rs2.getString(1));
				dto.setFileOrg(rs2.getString(2));
				System.out.print(rs2.getString(1));
			}
			
			rs2.close();
			pstmt2.close();
				
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn,  pstmt, rs);
		}

		return dto;
	}
	
	public int upHit(int no) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ConnectionDAO dao = ConnectionDAO.getInstance();

		String sql = "UPDATE COMMUNITY SET HIT = HIT + 1 WHERE NO = ?";
		
		try {
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			int r = pstmt.executeUpdate();
			if(r > 0) result = 1;
				
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn,  pstmt);
		}

		return result;
	}
	
	public int upLike(int no) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ConnectionDAO dao = ConnectionDAO.getInstance();

		String sql = "UPDATE COMMUNITY SET LIKES = LIKES + 1 WHERE NO = ?";
		
		try {
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			int r = pstmt.executeUpdate();
			if(r > 0) result = 1;
				
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn,  pstmt);
		}

		return result;
	}
	
	public int write(HttpServletRequest req) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2= null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		String sql, sql2  = null;
		
		
		File file = null;
		
		try {
			MultipartRequest multi = new MultipartRequest(req, UPLOAD, MAXSIZE, ENCTYPE, new DefaultFileRenamePolicy());
			conn = dao.getConnection();
			sql = "INSERT INTO COMMUNITY (NO, TITLE, CONTENT, WRITER) VALUES (COM_SEQ.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("title"));
			pstmt.setString(2, multi.getParameter("content"));
			pstmt.setString(3, multi.getParameter("id"));
			
			int r = pstmt.executeUpdate();
			
			if(r > 0) result = 1;
					
			if(multi.getFilesystemName("files") != null) {
						
					file = multi.getFile("files");                       
		            
					sql2 = "INSERT INTO FILE_COMMUNITY (CODE, FILE_NAME, FILE_ORG) VALUES (COM_SEQ.CURRVAL, ?, ?)";
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, multi.getFilesystemName("files"));
					pstmt2.setString(2, file.getAbsolutePath());
					
					r = pstmt2.executeUpdate();
					
					if(r == 0) result = 0;
					
					pstmt2.close();
				}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn,  pstmt);
		}
		
		return result;
	}
	
	public int update(HttpServletRequest req) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2= null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		String sql, sql2  = null;
		int no = 0;
		
		try {
			sql = "UPDATE COMMUNITY SET TITLE = ?, CONTENT = ?, WDATE = SYSDATE WHERE NO = ?";
			MultipartRequest multi = new MultipartRequest(req, UPLOAD, MAXSIZE, ENCTYPE, new DefaultFileRenamePolicy());
			conn = dao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("title"));
			pstmt.setString(2, multi.getParameter("content"));
			no = Integer.parseInt(multi.getParameter("no"));
			pstmt.setInt(3, no);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0) result = 1;
			
			String path = this.getImgPath(no);
			File imgFile = new File(path);
			
			if(imgFile.exists()) imgFile.delete();
			
			File file = multi.getFile("files");
			
			sql2 = "UPDATE FILE_COMMUNITY SET FILE_NAME = ?, FILE_ORG = ?, INSERT_DATE = SYSDATE WHERE CODE = ?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, multi.getFilesystemName("files"));
			pstmt2.setString(2, file.getAbsolutePath());
			pstmt2.setInt(3,  no);
			
			pstmt2.executeUpdate();
			
			pstmt2.close();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn,  pstmt);
		}
		
		return no;
	}
	
	public int delete(int no) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt= null;
		PreparedStatement pstmt2= null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		String sql, sql2  = null;
		
		try {
			conn = dao.getConnection();
			sql = "DELETE FROM COMMUNITY WHERE NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  no);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0) result = 1;
			
			String path = this.getImgPath(no);
			File imgFile = new File(path);
			
			if(imgFile.exists()) imgFile.delete();
			
			sql2 = "DELETE FROM FILE_COMMUNITY WHERE CODE = ?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1,  no);
			
			
			pstmt2.close();
				
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn,  pstmt);
		}
		
		return result;
	}
	
	public String getImgPath(int code) {
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = null;
		String imgPath = null;
		try {
			conn = dao.getConnection();
			sql = "SELECT FILE_ORG FROM FILE_COMMUNITY WHERE CODE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				imgPath = rs.getString(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return imgPath;
	}
	

}
