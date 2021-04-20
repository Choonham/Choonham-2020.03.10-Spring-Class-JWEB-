<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 저장 처리</title>
</head>
<body>
<%
 	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String writer = request.getParameter("writer");
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver has been loaded!");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn = DriverManager.getConnection(url, "choonham", "6725");
		
		String sql = "INSERT INTO BOARD_TBL (NO, TITLE, CONTENT, WRITER) VALUES" +
				"(NO_SEQ.NEXTVAL, ?, ?, ?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);
		
		pstmt.execute();
		
	} catch(Exception e){
		e.printStackTrace();
	} finally {
		try{
			if(conn != null){
				conn.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	response.sendRedirect("list.jsp");  // 다시 list로 돌아가라
%>

</body>
</html>