<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	String no = request.getParameter("no");
	int n = Integer.parseInt(no);

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn = DriverManager.getConnection(url, "choonham", "6725");

		String sql = "SELECT NO, TITLE, CONTENT, WRITER, R_DATE, HIT FROM BOARD_TBL WHERE (NO = ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		rs = pstmt.executeQuery();
		rs.next();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>글 수정</title>
</head>
<body>
	<form action="update.jsp" method="get">
		<table>
			<tr>
				<th colspan="2">글 수정</th>
			</tr>
			<tr>
				<td>번호</td>
				<td><input type="text" size="48" name = "no" value='<%=rs.getInt("no")%>'
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" size="48"
					value='<%=rs.getString("title")%>' name="title" /></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="50" name="content"><%=rs.getString("content")%></textarea></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" size="48"
					value='<%=rs.getString("writer")%>' readonly="readonly" /></td>
			</tr>
			<tr>
				<td>날짜</td>
				<td><input type="text" size="48"
					value='<%=rs.getDate("r_date")%>' readonly="readonly" /></td>
			</tr>
			<tr>
				<td>조회수</td>
				<td><input type="text" size="48" value='<%=rs.getInt("hit")%>' readonly="readonly" /></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="수정" />
				<input type="button" value="취소"onclick="location.href = 'detail.jsp?no=<%=rs.getInt(1)%>'" /> 
					<input type="button" value="목록" onclick="location.href = 'list.jsp'" /></td>
			</tr>
		</table>
	</form>
	<%
	}catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	%>
</body>
</html>