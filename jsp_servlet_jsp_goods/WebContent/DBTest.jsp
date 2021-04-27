<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="DBError.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<%!
	// 클래스의 전역(멤버)변수처럼 선언 할 경우 (특히 메서드 선언은 반드시 필요)
	String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	String userid="choonham";
	String userpw="6725";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오라클 접속 테스트</title>
</head>

<body>
	<h3>데이터베이스 연결 테스트</h3>
	<%
		// 1. 데이터베이스(JDBC) 드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2. 데이터베이스 연결 객체 Connection 생성(얻기)
		Connection conn=DriverManager.getConnection(url, userid, userpw);
		
		// 3. 데이터베이스 연결 확인
		if(conn != null){
			out.print("연결 되었습니다.<br />");
			conn.close();
			out.print("연결이 끊겼습니다.<br />");
		}else{
			out.print("연결할 수 없습니다.<br />");
		}
	%>
</body>
</html>




