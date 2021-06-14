<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>이전 페이지 URL/URI</title>
</head>

<body>
	<c:set var="refererURL" value="${header.referer}" />
	이전 페이지 URL 은 : ${refererURL}
	<br />
	<c:set var="URL" value="${pageContext.request.requestURL}" />
	현재 URL 가져오기 : ${URL}
	<br />
	<c:set var="URI" value="${pageContext.request.requestURI}" />
	현재 URI 가져오기 : ${URI}
</body>
</html>