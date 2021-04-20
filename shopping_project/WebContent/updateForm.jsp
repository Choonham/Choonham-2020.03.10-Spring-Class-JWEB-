<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
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
		
		String sql = "SELECT KIND, NAME, PRICE1, PRICE2, PRICE3, BESTYN, USEYN, CONTENT FROM PRODUCT WHERE PSEQ = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pseq);
		
		rs = pstmt.executeQuery();
		
		rs.next();

%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
여기는 상품 수정 페이지입니다.
<form action = "update.jsp" method = "get"> 
	<table>
				<tr align = "center">
						<th colspan = "6">상품 수정</th>
				</tr>
				<tr> 
					<td> 상품 분류 </td> <!--  모르겠음...  -->
					<td>  
						<select name="kind">
							<option value = "1">heals</option>
							<option value = "2">shcakers</option>
							<option value = "3">sandals</option>
							<option value = "4">slippers</option>
						</select>
					</td>	
				</tr>
				<tr>
					<td> 상품명 </td>
					<td colspan = "3"> <input type = "text" name = "name" required = "required" size = "60"/> <%=rs.getString("NAME") %> </td>
				</tr>
				<tr>
					<td colspan = "1"> 원가[A] </td>
					<td colspan = "1"> <input type = "text" name = "price1" required = "required"/><%=rs.getInt("PRICE1") %> </td>	
					<td colspan = "1"> 판매가[B] </td>
					<td colspan = "1"> <input type = "text" name = "price2" required = "required"/><%=rs.getInt("PRICE2") %></td>	
					<td colspan = "1"> [B-A] </td>
					<td colspan = "1"> <input type = "text" name = "price3" required = "required"/><%=rs.getInt("PRICE3") %></td>	
				</tr>
				<tr>
					<td  colspan = "1">
						베스트 상품
					</td>
					<td colspan = "1">
					<% if(rs.getString("BESTYN").equals("y")) { %>
						<input type = "checkbox" name = "best" value = "y" checked = "checked">
						<% } else { %>
						<input type = "checkbox" name = "best" value = "y">
					</td>
					<td colspan = "1">
						사용 유무
					</td>
					<td colspan = "2">
					<%} if(rs.getString("USEYN").equals("y")) { %>
						<input type = "checkbox" name = "used" value = "y" checked = "checked">
					<% } else { %>	
						<input type = "checkbox" name = "used" value = "y">
						<%} %>
					</td>
				</tr>
				<tr>
					<td> 상세 설명 </td>
					<td colspan = "5"> 
						<textarea rows="20" cols="100" name="content" required="required">
							<%= rs.getString("CONTENT") %>
						</textarea>
					</td>
				</tr>
				<tr align = "center"> 
					<td colspan = "6"> 
						<input type = "submit" value = "수정"/>
						<input type = "button" value = "취소" onclick = "location.href='detail.jsp?no=<%= pseq %>'"/>
						<input type = "hidden" name = "no" value = "<%= pseq %>"/>
					</td>
				</tr>	
	</table>
</form>

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