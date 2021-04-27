<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GoodsInfoViewer.jsp : 상품 정보 출력</title>
</head>

<body>
	<h3>상품 정보</h3>
	코드 : ${CODE } <br />
	제목 : ${TITLE } <br />
	저자 : ${WRITER } <br />
	가격 : ${PRICE } 원 <br />
	가격 : <fmt:formatNumber value="${PRICE }" type="currency" /> <br />
	
	<input type="button" value="수정" onclick="document.location.href='GoodsInfoEditForm.jsp'"/>
	
</body>
</html>

