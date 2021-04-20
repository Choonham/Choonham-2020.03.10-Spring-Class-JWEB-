<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmtHit = null;
	ResultSet rs = null;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn = DriverManager.getConnection(url, "choonham", "6725");

		String no = request.getParameter("no");
		String sqlHit = "UPDATE BOARD_TBL SET HIT = HIT + 1 WHERE NO = ?";
		String sql = "SELECT NO, TITLE, CONTENT, WRITER, R_DATE, HIT FROM BOARD_TBL WHERE (NO = ?)";
		pstmtHit = conn.prepareStatement(sqlHit);
		pstmtHit.setString(1, no);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		pstmtHit.executeUpdate();
		rs = pstmt.executeQuery();
		rs.next();
%>

<title><%= rs.getString(2) %></title>
</head>
<body>
	<table> 
		<tr><th colspan = "2"> 글 상세보기 화면</th></tr>
		<tr> 
			<td> 번호 </td>
			<td><input type = "text"  size = "48" value = '<%= rs.getInt("no") %>' readonly = "readonly" /> </td>
		</tr>
		<tr> 
			<td> 제목 </td>
			<td><input type = "text"  size = "48" value = '<%= rs.getString("title")%>' readonly = "readonly" /> </td>
		</tr>
		<tr> 
			<td> 내용 </td>
			<td><textarea rows = "10" cols = "50" readonly = "readonly"><%= rs.getString("content") %></textarea></td>
		</tr>
		<tr> 
			<td> 작성자 </td>
			<td><input type = "text"  size = "48" value = '<%= rs.getString("writer")%>' readonly = "readonly" /> </td>
		</tr>
		<tr> 
			<td> 날짜 </td>
			<td><input type = "text"  size = "48" value = '<%= rs.getDate("r_date")%>' readonly = "readonly" /> </td>
		</tr>
		<tr> 
			<td> 조회수 </td>
			<td><input type = "text"  size = "48" value = '<%= rs.getInt("hit")%>' readonly = "readonly" /> </td>
		</tr>
		<tr align = "center"> 
			<td colspan = "2">  
			 	<input type = "button" value = "수정" onclick = "location.href = 'updateForm.jsp?no=<%=rs.getInt(1)%>'"/>
			 	<input type = "button" value = "삭제" onclick = "location.href = 'delete.jsp?no=<%=rs.getInt(1)%>'"/>
			 	<input type = "button" value = "목록" onclick = "location.href = 'list.jsp'"/>
			 </td>
		</tr>
	</table>

	<%
	
	} 
	catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if(pstmtHit != null)
				pstmtHit.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	%>
</body>
</html>