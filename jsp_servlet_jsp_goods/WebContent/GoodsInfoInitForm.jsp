<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import= "java.sql.DriverManager"%>
<%@ page import= "java.sql.ResultSetMetaData" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.choonham.dto.Goods" %>

<%
	ArrayList<Goods> goodsList = (ArrayList<Goods>)request.getAttribute("Goods_List");
	pageContext.setAttribute("Goods", goodsList);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 정보 관리</title>
</head>

<body>
	<h3>상품코드를 입력하십시오.</h3>
	<form action="GoodsInfoSearch.jsp">
		상품코드: <input type="text"  name="code" size="5" maxlength="5"/>
		<input type="submit" value="확인" /> <br />
		<h3>상품 목록</h3>
		<table> 
			<c:forEach var = "goods" items = "${Goods }" varStatus = "status" >
				<tr>
					<td>${status.count }</td>
					<td><a href = "GoodsInfoReader.jsp?code=${goods.getCode() }">${goods.getCode() }</a></td>
					<td>${goods.getTitle() }</td>
					<td>${goods.getWriter() }</td>
					<td>${goods.getPrice() }</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>










