<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
여기는 상품 상세 페이지입니다.
<%
	String no = request.getParameter("no");	
	int pseq = Integer.parseInt(no);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver has been loaded!");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		conn = DriverManager.getConnection(url, "choonham", "6725");
		
		String sql = "SELECT KIND, NAME, PRICE1, PRICE2, PRICE3, CONTENT, IMAGE, PSEQ FROM PRODUCT WHERE PSEQ = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pseq);
		
		rs = pstmt.executeQuery();
		rs.next();
%>

	<table>
				<tr align = "center">
						<th colspan = "6">상품 상세 보기</th>
				</tr>
				<tr> 
					<td> 상품 분류 </td>
					<td>  
						<% 
						if(rs.getString("KIND").equals("1")) out.print("heals");
						else if(rs.getString("KIND").equals("2")) out.print("Shcakers");
						else if(rs.getString("KIND").equals("3")) out.print("sandals");
						else out.print("slipper");
						%>
					</td>	
				</tr>
				<tr>
					<td> 상품명 </td>
					<td colspan = "4"> <%=rs.getString("NAME") %> </td>
				</tr>
				<tr>
					<td colspan = "1"> 원가[A] </td>
					<td colspan = "1"> <%= rs.getInt("PRICE1") %></td>	
					<td colspan = "1"> 판매가[B] </td>
					<td colspan = "1"> <%=rs.getInt("PRICE2") %></td>	
					<td colspan = "1"> [B-A] </td>
					<td colspan = "1"> <%=rs.getInt("PRICE3") %></td>	
				</tr>
				<tr>
					<td> 상세 설명 </td>
					<td colspan = "5"> 
						<%=rs.getString("CONTENT") %>
					</td>
				</tr>
				<tr>
					<td>상품 이미지</td>
					<td colspan = "5"><img src="product_images/<%=rs.getString(7) %>"  width="500" height="600"></td>
				</tr>
				<tr align = "center"> 
					<td colspan = "6"> 
						<input type = "button" value = "수정" onclick = "location.href='updateForm.jsp?no=<%= rs.getInt(8) %>'"/>
						<input type = "button" value = "목록" onclick = "location.href='list.jsp'"/>
					</td>
				</tr>	
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