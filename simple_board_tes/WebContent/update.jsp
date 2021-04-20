<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	conn = DriverManager.getConnection(url, "choonham", "6725");
	
	String no = request.getParameter("no");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	int n = Integer.parseInt(no);
	String sql = "UPDATE BOARD_TBL SET TITLE = ? , CONTENT = ?, R_DATE = SYSDATE WHERE NO = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, title);
	pstmt.setString(2, content);
	pstmt.setInt(3, n);
	int r = pstmt.executeUpdate();
    if(r > 0) 
    	response.sendRedirect("detail.jsp?no="+n);
} catch(Exception e){
	e.printStackTrace();
} finally{
	try{
		if(conn != null) conn.close();
		if(pstmt != null) pstmt.close();
	} catch(Exception e){
		e.printStackTrace();
	}
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
	글 수정 중...
</body>
</html>