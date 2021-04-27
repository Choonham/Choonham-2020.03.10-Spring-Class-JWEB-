<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="DBError.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>

<%! 
  	// 접속주소 / 아이디 / 비밀번호 / 필요한 객체 선언
	String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	String userid="choonham";
	String userpw="6725";
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	String query="update goodsinfo set title=?,  writer=?, price=? where code=?";
%>
<%
	request.setCharacterEncoding("UTF-8");
	String code = request.getParameter("code");
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String price = request.getParameter("price");
	
	
	// GoodsInfoReader.jsp : goodsinfo 테이블로부터 code 값을 이용하여 검색
	// 검색된 결과 값을 GoodsInfoViewer.jsp 에게 전달 후, 자원(리소스)를 해제
	try{
		// 1. JDBC 드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2. 데이터베이스 연결 객체 생성(얻기)
		conn=DriverManager.getConnection(url, userid, userpw);
		
		// 3. PreparedStatement  객체 생성(얻기)
		pstmt=conn.prepareStatement(query);
		
		// 4. ?를 바인딩(치환)
		pstmt.setString(4, code);	
		pstmt.setString(1, title);	
		pstmt.setString(2, writer);	
		pstmt.setInt(3, Integer.parseInt(price));	
				
		// 5. SQL 쿼리문 실행 요청후 결과 처리
		int n=pstmt.executeUpdate();   // SQL 쿼리문 실행 요청
		
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

	response.sendRedirect("GoodsInfoInitForm.jsp");
%>








