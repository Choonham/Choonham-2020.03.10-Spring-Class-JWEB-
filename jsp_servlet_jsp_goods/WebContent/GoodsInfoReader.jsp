<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="DBError.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@page import="java.sql.DriverManager"%>

<%! 
  	// 접속주소 / 아이디 / 비밀번호 / 필요한 객체 선언
	String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	String userid="choonham";
	String userpw="6725";
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	String sql=null;
%>
<%
	request.setCharacterEncoding("UTF-8");
	String code = request.getParameter("code");
	sql = "SELECT * FROM GOODSINFO WHERE CODE = ?";
	try{
		// 1. JDBC 드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		conn=DriverManager.getConnection(url, userid, userpw);
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, code);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		session.setAttribute("CODE", rs.getString("code"));
		session.setAttribute("TITLE", rs.getString("title"));
		session.setAttribute("WRITER", rs.getString("writer"));
		session.setAttribute("PRICE", rs.getInt("price"));
		
	}finally{
		// 6. 자원(리소스) 해제
		try{
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(Exception e){
		}
	}

	response.sendRedirect("GoodsInfoViewer.jsp");
%>








