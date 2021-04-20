<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<%
	String name = request.getParameter("name");
  	String kind = request.getParameter("kind");
  	String price1 = request.getParameter("price1");
  	String price2 = request.getParameter("price2");
  	String price3 = request.getParameter("price3");
  	String content = request.getParameter("content");
  	
  	int priceInt1= Integer.parseInt(price1);
  	int priceInt2 = Integer.parseInt(price2);
  	int priceInt3 = Integer.parseInt(price3);
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver has been loaded!");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn = DriverManager.getConnection(url, "choonham", "6725");
		
		String sql = "INSERT INTO PRODUCT (PSEQ, NAME, KIND, PRICE1, PRICE2, PRICE3, CONTENT)" +
				"VALUES (PRO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, kind);
		pstmt.setInt(3, priceInt1);
		pstmt.setInt(4, priceInt2);
		pstmt.setInt(5, priceInt3);
		pstmt.setString(6, content);
		
		int r = pstmt.executeUpdate();
%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(r > 0) response.sendRedirect("list.jsp"); 
%>
여기는 업로드 동작 페이지입니다.
<%
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}

%>
<a href = "list.jsp"> 목록으로(임시 디렉션입니다.) </a>
</body>
</html>