<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page  import = "com.choonham.mpd.dao.FriendDAO" %>
<%@ page  import = "com.choonham.mpd.dao.MessageDAO" %>
<%@ page  import = "com.choonham.mpd.dto.MsgDTO" %>
<%@ page  import = "java.util.ArrayList" %>
<%
	request.setCharacterEncoding("UTF-8");
	String host = (String)session.getAttribute("idKey");
	String friId = request.getParameter("friend");
	MessageDAO dao = new MessageDAO();
	ArrayList<MsgDTO> msgList = dao.getHistory(host, friId);
	request.setAttribute("msgList", msgList);
	request.setAttribute("friend", friId);
%>

<br />
	<form action = "#">  
			  	<table  width="90%" border="1" align="center">
			  		<tr bordercolor="#f5f5f5" >
			  			<td colspan = "6" align="center" ><font size = "40">쪽지함</font></td>
			  		</tr>
			  		<tr align="center">
			  			<td width = "5%">No</td>
			 			<td width = "10%">receiver</td>
			 			<td width = "60%">content</td>
			 			<td width = "10%">Date</td>
			 			<td width = "10%">sender</td>
			  		</tr>
			  		<c:forEach var = "i" items = "${requestScope.msgList }">
			  			<tr>
				  			<td>${i.getCode() }</td>
				  			<td>${i.getTo() }</td>
				  			<td><a onclick = "javascript:msgView('${i.getCode() }')"><b>${i.getFirstLine(i.getContent()) }</b></a></td>
				  			<td>${i.getTime() }</td>
				  			<td>${i.getFrom() }</td>
			  			</tr>
			  		</c:forEach>
		
			  		<tr align = "right">
			  			<td colspan="6">
			  				<input type = "button" value ="쪽지 보내기" onclick="msgSend('${requestScope.friend }', '${sessionScope.idKey}')"/>
			  			</td>
			  		</tr>
			  	</table>
	</form>