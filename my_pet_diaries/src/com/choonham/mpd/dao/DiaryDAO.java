package com.choonham.mpd.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.choonham.mpd.dto.DiaryDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class DiaryDAO {

	private static final String UPLOAD = "C:/workspace_jweb/my_pet_diaries/WebContent/imgs/";
	private static final String ENCTYPE = "UTF-8";
	private static final int MAXSIZE = 10*1024*1024;
	
	public DiaryDAO() {
		
	}
	
	//img 불러오기
	public HashMap<String, ArrayList<String>> getImg(String writer, int isPublic) {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = dao.getConnection();
			
			if(isPublic == 0) { // 다이어리 주인이 접근
				sql = "SELECT CODE, FILE_NAME FROM FILE_DIARY WHERE WRITER = ? ORDER BY CODE DESC";
			} else if(isPublic == 1) { // 다이어리 주인과 친구 혹은 가족인 계정이 접근
				sql = "SELECT CODE, FILE_NAME FROM FILE_DIARY WHERE WRITER = ? AND (ISPUBLIC = 0 OR ISPUBLIC = 1) ORDER BY CODE DESC";
			} else if(isPublic == 2) { // 관계가 없는 다른 계정이 접근
				sql = "SELECT CODE, FILE_NAME FROM FILE_DIARY WHERE WRITER = ? AND ISPUBLIC = 0 ORDER BY CODE DESC";
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String code = rs.getString("CODE");
				String name = rs.getString("FILE_NAME");
				if(map.containsKey(code)) {
					map.get(code).add(name);
				} else {
					ArrayList<String> list = new ArrayList<String>();
					list.add(name);
					map.put(code, list);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return map;
	}
	
	// 내 일기 불러오기(인덱스 번호), 파일 제외
	public ArrayList<DiaryDTO> getMyList(String writer, int isPublic){
		ArrayList<DiaryDTO> list = new ArrayList<DiaryDTO>();
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		String sql  = null;
		
		try {
			conn = dao.getConnection();
			if(isPublic == 0) { // 다이어리 주인이 접근
				sql = "SELECT * FROM DIARY WHERE WRITER = ? ORDER BY DIARY_CODE DESC";
			} else if(isPublic == 1) { // 다이어리 주인과 친구 혹은 가족인 계정이 접근
				sql = "SELECT * FROM DIARY WHERE WRITER = ? AND (ISPUBLIC = 0 OR ISPUBLIC = 1) ORDER BY DIARY_CODE DESC";
			} else if(isPublic == 2) { // 관계가 없는 다른 계정이 접근
				sql = "SELECT * FROM DIARY WHERE (WRITER = ? AND ISPUBLIC = 0) ORDER BY DIARY_CODE DESC";
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  writer);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DiaryDTO dto = new DiaryDTO();
				dto.setTitle(rs.getString("TITLE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setWdate(rs.getString("WDATE"));
				dto.setIsPublic(rs.getInt("ISPUBLIC"));
				dto.setLikes(rs.getInt("LIKES"));
				dto.setCode(rs.getString("DIARY_CODE"));
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return list;
	}
	
	@Deprecated
	//현재 작성할 일기의 글 번호 가져오기
	public String getCode() {
		String code = null;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		String sql  = null;
		try {
			conn = dao.getConnection();
			sql = "SELECT MAX(DIARY_CODE) FROM DIARY";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				code = rs.getString(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt, rs);
		}
		return code;
	}
	
	// 일기 쓰기
	public boolean writeDiary(HttpServletRequest req) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt= null;
		PreparedStatement pstmt2= null;
		File file = null;
		String sql, sql2  = null;
		int isPublic = 0;
		try {
			MultipartRequest multi = new MultipartRequest(req, UPLOAD, MAXSIZE, ENCTYPE, new DefaultFileRenamePolicy());
			conn = dao.getConnection();
			sql = "INSERT INTO DIARY (TITLE, WRITER, CONTENT, ISPUBLIC, LIKES, DIARY_CODE) VALUES (?, ?, ?, ?, 0, DIARY_SEQ.NEXTVAL)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, multi.getParameter("title"));
			pstmt.setString(2, multi.getParameter("writer"));
			pstmt.setString(3, multi.getParameter("content"));
			if(multi.getParameter("isPublic").equals("publicAll")) {
				isPublic = 0;
				pstmt.setInt(4, isPublic);
			} else if(multi.getParameter("isPublic").equals("publicRel")) {
				isPublic = 1;
				pstmt.setInt(4, isPublic);
			} else {
				isPublic = 2;
				pstmt.setInt(4, isPublic);
			}
			int r = pstmt.executeUpdate();
			if(r > 0) result  = true;
			
			
			if(multi.getFilesystemName("files") != null) {
				
				file = multi.getFile("files");                       
				sql2 = "INSERT INTO FILE_DIARY (CODE, FILE_NAME, FILE_ORG, WRITER, ISPUBLIC) VALUES (DIARY_SEQ.CURRVAL, ?, ?, ?, ?)";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, multi.getFilesystemName("files"));
				pstmt2.setString(2, file.getAbsolutePath());
				pstmt2.setString(3, multi.getParameter("writer"));
				pstmt2.setInt(4, isPublic);
				
				r = pstmt2.executeUpdate();
				
				if(r == 0) result = false;
				
				pstmt2.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	//파일 저장(사용하지 않는다)
	@Deprecated
	public boolean uploadFile(int code, HttpServletRequest req) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql  = null;
		String c = Integer.toString(code);
		try {
			MultipartRequest multi = new MultipartRequest(req, UPLOAD, MAXSIZE, ENCTYPE, new DefaultFileRenamePolicy());
			conn = dao.getConnection();
			sql = "INSERT INTO FILE_DIARY (CODE, FILE_NAME, FILE_ORG) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c);
			pstmt.setString(2, multi.getFilesystemName("files"));
			pstmt.setString(3, multi.getOriginalFileName("files"));
			int r = pstmt.executeUpdate();
			
			if(r>0) result = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	// 일기 수정
	public boolean editDiary(HttpServletRequest req) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt= null;
		PreparedStatement pstmt2= null;
		File file = null;
		String sql, sql2  = null;
		int isPublic = 0;
		try {
			MultipartRequest multi = new MultipartRequest(req, UPLOAD, MAXSIZE, ENCTYPE, new DefaultFileRenamePolicy());

			conn = dao.getConnection();
			sql = "UPDATE DIARY SET TITLE = ?, CONTENT = ?, ISPUBLIC = ? WHERE DIARY_CODE = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, multi.getParameter("title"));
			pstmt.setString(2, multi.getParameter("content"));
			if(multi.getParameter("isPublic").equals("publicAll")) {
				isPublic = 0;
				pstmt.setInt(3, isPublic);
			} else if(multi.getParameter("isPublic").equals("publicRel")) {
				isPublic = 1;
				pstmt.setInt(3, isPublic);
			} else {
				isPublic = 2;
				pstmt.setInt(3, isPublic);
			}
			String c = multi.getParameter("code").trim();
			int code = Integer.parseInt(c);
			
			pstmt.setInt(4, code);
			int r = pstmt.executeUpdate();
			
			if(r > 0) result  = true;
			System.out.print(result);
			
			if(multi.getFilesystemName("files") != null) {                
                
				String path = this.getImgPath(code);
				
				File imgFile = new File(path);
				
				if(imgFile.exists()) imgFile.delete();
				
				file = multi.getFile("files");
				
				sql2 = "UPDATE FILE_DIARY SET FILE_NAME = ?, FILE_ORG = ? WHERE CODE = ?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, multi.getFilesystemName("files"));
				pstmt2.setString(2, file.getAbsolutePath());
				pstmt2.setInt(3, code);
				
				r = pstmt2.executeUpdate();
				
				if(r == 0) result = false;
				System.out.print(result);
				pstmt2.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return result;
	}
	
	// 일기 삭제
	public boolean deleteDiary(String code) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt= null;
		String sql = null;
		
		int c = Integer.parseInt(code);
		
		try {
			conn = dao.getConnection();
			sql = "DELETE FROM DIARY WHERE DIARY_CODE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0) 
				result = true;
			
			pstmt.close();
			
			String path = this.getImgPath(c);
			
			File imgFile = new File(path);
			
			if(imgFile.exists()) imgFile.delete();
			
			sql = "DELETE FROM FILE_DIARY WHERE CODE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			sql = "DELETE FROM LIKE_SET WHERE CODE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		
		return result;
	}
	
	// 이미지 파일 경로 추출
	public String getImgPath(int code) {
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = null;
		String imgPath = null;
		try {
			conn = dao.getConnection();
			sql = "SELECT FILE_ORG FROM FILE_DIARY WHERE CODE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				imgPath = rs.getString(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}
		return imgPath;
	}
	
	// 좋아요를 누른 목록 추출
	public HashMap<String, ArrayList<String>> getLikes(String writer, int pool) {
		HashMap<String, ArrayList<String>> likeMap = new HashMap<String, ArrayList<String>>();
		
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = dao.getConnection();
			sql = "SELECT CODE, NAME FROM LIKE_SET WHERE  WRITER = ? AND POOL = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  writer);
			pstmt.setInt(2,  pool);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				
				if(likeMap.get(code)==null) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(name);
					likeMap.put(code, list);
				} else {
					likeMap.get(code).add(name);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt,rs);
		}
		
		return likeMap;
	}
	
	
	// 좋아요 기능(pool: 1 => 게시판, pool: 0 => 다이어리)
	public boolean pushLike(int pool, String code, String id, String writer) {
		boolean result = false;
		ConnectionDAO dao = ConnectionDAO.getInstance();
		Connection conn = null;
		PreparedStatement pstmt= null;
		String sql = null;
		
		try {
			conn = dao.getConnection();
			sql = "INSERT INTO LIKE_SET VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pool);
			pstmt.setString(2,  code);
			pstmt.setString(3, id);
			pstmt.setString(4, writer);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0) result = true;
			
			pstmt.close();
			
			sql = "UPDATE DIARY SET LIKES = LIKES + 1 WHERE DIARY_CODE = ?";
			pstmt = conn.prepareStatement(sql);
			String codeTrimed = code.trim();
			int c = Integer.parseInt(codeTrimed);
			pstmt.setInt(1,  c);
			
			r = pstmt.executeUpdate();
			
			if(r == 0) result = false;
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dao.freeConnection(conn, pstmt);
		}

		return result;
	}

}
