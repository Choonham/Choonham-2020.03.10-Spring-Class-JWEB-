<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>List Page</title>
</head>

<body>
		여기는 메인 리스트 페이지입니다.
		<%
		String searchVal = request.getParameter("search");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData md = null;
		String sql = null;
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("Driver has been loaded!");
				String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
				conn = DriverManager.getConnection(url, "choonham", "6725");
				
			
			if(searchVal == null){
				sql = "SELECT PSEQ, NAME, PRICE1, PRICE2, INDATE FROM PRODUCT";
				pstmt = conn.prepareStatement(sql);
			}
			else {
				sql = "SELECT PSEQ, NAME, PRICE1, PRICE2, INDATE FROM PRODUCT WHERE NAME LIKE \'%\' || ? ||  \'%\'";
				// pstmt 로 like를 사용할 때는 반드시 위와 같이 해줘야 에러가 안난다....(ㅂㄷㅂㄷ...)
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "리");
			}
	 	 	rs = pstmt.executeQuery();
	 	 	md = rs.getMetaData();
			int n = md.getColumnCount();
		%>
		<table>
			<tr align = "center">
					<th>상품 리스트</th>
			</tr>
			<tr align = "center">
				<td colspan = "5">
					<form action = "list.jsp" method = "get">
						<input type = "text" name = "search" required = "required"/>
						<input type = "submit" value = "  검색  "/>
						<input type = "button" value = "전체보기" onclick = "location.href = 'list.jsp'"/>
						<input type = "button" value = "상품등록" onclick = "location.href = 'uploadForm.html'"/>
					</form>
				</td>
			</tr>
			<tr>
				<%
					for(int i = 1; i <= n; i++){
						%>
						<td> <%= md.getColumnLabel(i) %> </td>
						<%
					}
				%>
			</tr>
			<%
			while(rs.next()){
				%>
				<tr>
					<td><%= rs.getInt(1)%></td>
					<td><a href = "detail.jsp?no=<%=rs.getInt(1) %>"> <%= rs.getString(2)%> </a></td>
					<td><%= rs.getInt(3)%></td>
					<td><%= rs.getInt(4)%></td>
					<td><%= rs.getDate(5)%></td>
				</tr>
				<%
			}
			%>
		</table>
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
	</body>
</html>