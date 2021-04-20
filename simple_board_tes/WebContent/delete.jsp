<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제 처리</title>
</head>
<body>
글 삭제 중...
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn = DriverManager.getConnection(url, "choonham", "6725");
		
		String no = request.getParameter("no");
		int n = Integer.parseInt(no);
		String sql = "DELETE FROM BOARD_TBL WHERE NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, n);
		int r = pstmt.executeUpdate();
	    if(r > 0) 
	    	response.sendRedirect("list.jsp");
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

</body>
</html>