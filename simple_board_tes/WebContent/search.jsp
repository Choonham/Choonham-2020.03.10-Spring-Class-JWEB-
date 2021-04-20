<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<body>
		글 목록
	
	<br /><br />
	<form action = "search.jsp" method = "get">
		<input type = "text" name  = "searchWord" required = "required"/>
		<input type = "submit" value = "검색"/>
	</form>
	

<a href = "writeForm.html"> 글쓰기 </a>
<a href = "detail.jsp"> 상세 </a>


<table border = "1">
	<tr> <th colspan = "8"> 글 목록 </th></tr>
	<tr align="center"> 
				<td> 번호 </td>
				<td colspan = "4"> 제목 </td>
				<td> 작성자 </td>
				<td> 작성일 </td>
				<td> 조회수 </td>
		</tr>
<%
		
	String searchWord = request.getParameter("searchWord");

	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver has been loaded!");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn = DriverManager.getConnection(url, "choonham", "6725");
		
		String sql = "SELECT  NO, TITLE, WRITER, TO_CHAR(R_DATE, \'YY/MM/DD\'), HIT FROM BOARD_TBL WHERE TITLE LIKE \'%\' || ? || \'%\'";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchWord);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {

				out.print("<tr align=\"center\">");
				out.print("<td>" + rs.getInt(1) + "</td>");
				out.print("<td colspan = 4> <a href = detail.jsp?no=" + rs.getInt(1)+" >" + rs.getString(2) + "</a></td>");
				out.print("<td>" + rs.getString(3) + "</td>");
				out.print("<td>" + rs.getString(4) + "</td>");
				out.print("<td>" + rs.getInt(5) + "</td>");
				out.print("</tr>");
			
		}
	}catch(Exception e){
		e.printStackTrace();
	}

%>

</table>

</body>
</html>