<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="DBError.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.choonham.dto.Goods" %>

<%! 
  	// 접속주소 / 아이디 / 비밀번호 / 필요한 객체 선언
	String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	String userid="choonham";
	String userpw="6725";
	
	Connection conn=null;
	Statement stmt = null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	ArrayList<Goods> goodsList = null;
	
	String sql="SELECT * FROM GOODSINFO";
	String searchSql = "SELECT * FROM GOODSINFO WHERE CODE LIKE '%' || ? || '%'";
%>
<%
	try{
		// 1. JDBC 드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection(url, userid, userpw);
		String search = request.getParameter("code");
		
		if(search.equals("")){
			System.out.print("야");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} else {
			System.out.print("호");
			pstmt = conn.prepareStatement(searchSql);
			pstmt.setString(1, search);
			rs = pstmt.executeQuery();
		}
			
		goodsList = new ArrayList<Goods>();
		Goods  goods = null;

		while(rs.next()){
			String code = rs.getString("code");
			String title=rs.getString("title");
			String writer=rs.getString("writer");
			int price=rs.getInt("price");
			
			goods = new Goods();
			goods.setCode(code);
			goods.setTitle(title);
			goods.setWriter(writer);
			goods.setPrice(price);
			
			goodsList.add(goods);
		}
		request.setAttribute("Goods_List", goodsList);
		
	}finally{
		// 6. 자원(리소스) 해제
		try{
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
			if(stmt != null){
				stmt.close();
			}
		}catch(Exception e){
		}
	}

	RequestDispatcher rd=request.getRequestDispatcher("GoodsInfoInitForm.jsp");
	rd.forward(request, response);

%>








