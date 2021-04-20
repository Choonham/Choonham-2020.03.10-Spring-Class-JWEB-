<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	
	String no = request.getParameter("no");
	int pseq = Integer.parseInt(no);
	String name = request.getParameter("name");
	String kind = request.getParameter("kind");
	String price1 = request.getParameter("price1");
	int p1 = Integer.parseInt(price1);
	String price2 = request.getParameter("price2");
	int p2 = Integer.parseInt(price2);
	String price3 = request.getParameter("price3");
	int p3 = Integer.parseInt(price3);
	String content = request.getParameter("content");
	
	String best = request.getParameter("best");
	if(best == null) best = "n";
	
	String used = request.getParameter("used");
	if(used == null) used = "n";

	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver has been loaded!");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn = DriverManager.getConnection(url, "choonham", "6725");
		
		String sql = "UPDATE PRODUCT SET NAME = ?, KIND = ?, PRICE1 = ?, PRICE2 = ?, PRICE3 = ?, CONTENT = ?, BESTYN = ?, USEYN = ? " + 
		"WHERE PSEQ = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, name);
		pstmt.setString(2, kind);
		pstmt.setInt(3, p1);
		pstmt.setInt(4, p2);
		pstmt.setInt(5, p3);
		pstmt.setString(6, content);
		pstmt.setString(7, best);
		pstmt.setString(8, used);
		pstmt.setInt(9, pseq);
		
		int r = pstmt.executeUpdate();
		
		if(r>0) response.sendRedirect("detail.jsp?no="+ pseq);
		

%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
여기는 상품 수정 동작 페이지입니다.
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

<a href = "list.jsp"> 임시 디렉션입니다. </a>
</body>
</html>